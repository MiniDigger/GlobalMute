package me.minidigger.globalmute.backend.service;

import java.util.Optional;
import java.util.UUID;

import me.minidigger.globalmute.model.User;

public interface UserService {

    Optional<User> getUserByUsername(String username);

    Optional<User> getUserById(UUID id);
}
