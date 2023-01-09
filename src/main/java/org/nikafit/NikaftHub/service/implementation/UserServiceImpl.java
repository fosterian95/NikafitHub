package org.nikafit.NikaftHub.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nikafit.NikaftHub.model.User;
import org.nikafit.NikaftHub.repository.UserRepository;
import org.nikafit.NikaftHub.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;

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
    public Collection<User> list(int limit) {
        log.info("Fetching users");
        return userRepository.findAll(PageRequest.of(0, limit)).toList();
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
}
