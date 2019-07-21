package me.minidigger.globalmute.backend.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

import me.minidigger.globalmute.model.User;

public interface UserRepository extends CrudRepository<User, UUID> {

    Optional<User> findByUsername(String username);
}
