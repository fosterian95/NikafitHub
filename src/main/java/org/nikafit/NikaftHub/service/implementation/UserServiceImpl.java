package org.nikafit.NikaftHub.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nikafit.NikaftHub.model.User;
import org.nikafit.NikaftHub.repository.UserRepository;
import org.nikafit.NikaftHub.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public User create(User user) throws SQLException {
        log.info("Saving new user: {} {}", user.getFirstName(), user.getLastName());
        Connection connection = generateConnection();
        Statement statement = generateStatement(connection);
        ResultSet resultSet = statement
                .executeQuery(String.format("INSERT INTO users (firstname, lastname, position) " +
                                "VALUES ('%s', '%s', '%s')",
                        user.getFirstName(),
                        user.getLastName(),
                        user.getPosition()));
        User newUser = new User();
        while (resultSet.next()) {
            newUser.setId(resultSet.getLong("id"));
            newUser.setFirstName(resultSet.getString("firstname"));
            newUser.setLastName(resultSet.getString("lastname"));
            newUser.setPosition(resultSet.getString("position"));
        }
        closeConnection(connection);
        return newUser;
//        return userRepository.save(user);
    }

    @Override
    public Collection<User> listUsers(int limit) throws SQLException {
        log.info("Fetching users");
        Connection connection = generateConnection();
        Statement statement = generateStatement(connection);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        List<User> users = new ArrayList<>();
        while(resultSet.next()) {
            users.add(new User(resultSet.getLong("id"),
                    resultSet.getString("firstname"),
                    resultSet.getString("lastname"),
                    resultSet.getString("position")));
        }
        closeConnection(connection);
        return users;
    }

    @Override
    public User getUser(Long id) throws SQLException {
        log.info("Fetching user " + id);
        Connection connection = generateConnection();
        Statement statement = generateStatement(connection);
        ResultSet resultSet = statement
                .executeQuery("SELECT * FROM users WHERE id = " + id);
        User user = new User();
        while(resultSet.next()) {
            user.setId(resultSet.getLong("id"));
            user.setFirstName(resultSet.getString("firstname"));
            user.setLastName(resultSet.getString("lastname"));
            user.setPosition(resultSet.getString("position"));
        }
        closeConnection(connection);
        return user;
    }

    @Override
    public User update(User user) throws SQLException {
        log.info("Updating user: {}", user.getId());
        Connection connection = generateConnection();
        Statement statement = generateStatement(connection);
        ResultSet resultSet = statement
                .executeQuery(String.format("UPDATE users SET firstname = '%s', " +
                        "lastname = '%s', position = '%s' " +
                        "WHERE id = " + user.getId()));
        User updatedUser = new User();
        while(resultSet.next()) {
            updatedUser.setId(resultSet.getLong("id"));
            updatedUser.setFirstName(resultSet.getString("firstname"));
            updatedUser.setLastName(resultSet.getString("lastname"));
            updatedUser.setPosition(resultSet.getString("position"));
        };
        closeConnection(connection);
        return updatedUser;
//        return userRepository.save(user);
    }

    @Override
    public Boolean delete(Long id) throws SQLException {
        log.info("Deleting user: {}", id);
        Connection connection = generateConnection();
        Statement statement = generateStatement(connection);
        statement.execute(String.format("DELETE FROM users WHERE id='%s'", id));
//        userRepository.deleteById(id);
        return Boolean.TRUE;
    }

    private Statement generateStatement(Connection con) {
        Statement statement;

        {
            try {
                statement = con.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return statement;
    }

    private Connection generateConnection() {
        Connection con;

        {
            try {
                con = DriverManager.getConnection(System.getenv("NIKAFIT_HUB_DATASOURCE_URL"),
                        System.getenv("NIKAFIT_HUB_DATASOURCE_USERNAME"),
                        System.getenv("NIKAFIT_HUB_DATASOURCE_PASSWORD"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return con;
    }

    private void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
