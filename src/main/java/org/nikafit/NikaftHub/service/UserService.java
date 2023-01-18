package org.nikafit.NikaftHub.service;

import org.nikafit.NikaftHub.model.User;

import java.sql.SQLException;
import java.util.Collection;

public interface UserService {
    User create(User user) throws SQLException;
    Collection<User> listUsers(int limit) throws SQLException;
    User getUser(Long id) throws SQLException;
    User update(User user) throws SQLException;
    Boolean delete(Long id) throws SQLException;
}
