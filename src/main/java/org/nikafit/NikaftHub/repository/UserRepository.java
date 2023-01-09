package org.nikafit.NikaftHub.repository;

import org.nikafit.NikaftHub.enumeration.Position;
import org.nikafit.NikaftHub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findAllByPosition(Position position);
}
