package me.minidigger.globalmute.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

import me.minidigger.globalmute.backend.service.UserService;
import me.minidigger.globalmute.model.User;

@RestController
@RequestMapping("/api/secure/users")
class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/{id}")
    Optional<User> showUserForm(@PathVariable("id") UUID id) {
        return userService.getUserById(id);
    }
}