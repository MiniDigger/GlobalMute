package me.minidigger.globalmute.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import me.minidigger.globalmute.backend.service.UserService;
import me.minidigger.globalmute.backend.util.TokenUtil;
import me.minidigger.globalmute.model.User;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public LoginController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(@RequestBody User login) {
        if (login.getUsername() == null || login.getPassword() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please fill in username and password");
        }

        String username = login.getUsername();
        String password = login.getPassword();

        Optional<User> user = userService.getUserByUsername(username);

        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found.");
        }

        if (!passwordEncoder.matches(password, user.get().getPassHash())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid login. Please check your name and password.");
        }

        user.get().setToken(TokenUtil.createToken(username));
        user.get().setPassHash(null); // don't leak hash
        return user.get();
    }
}
