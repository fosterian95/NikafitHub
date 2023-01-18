package org.nikafit.NikaftHub.service;

import org.nikafit.NikaftHub.model.User;

import java.sql.SQLException;
import java.util.Collection;

public interface UserService {
    User create(User user);
    Collection<User> list(int limit) throws SQLException;
    User get(Long id);
    User update(User user);
    Boolean delete(Long id);
}
