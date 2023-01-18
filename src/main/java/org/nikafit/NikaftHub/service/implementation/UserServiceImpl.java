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
    public User create(User user) {
        log.info("Saving new user: {} {}", user.getFirstName(), user.getLastName());
        return userRepository.save(user);
    }

    @Override
    public Collection<User> list(int limit) throws SQLException {
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
    public User get(Long id) {
        log.info("Fetching server be id: {}", id);
        return userRepository.findById(id).get();
    }

    @Override
    public User update(User user) {
        log.info("Updating user: {}", user.getId());
        return userRepository.save(user);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server: {}", id);
        userRepository.deleteById(id);
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
