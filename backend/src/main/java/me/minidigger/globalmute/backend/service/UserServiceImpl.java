package me.minidigger.globalmute.backend.service;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import me.minidigger.globalmute.backend.repository.UserRepository;
import me.minidigger.globalmute.model.User;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }
}
