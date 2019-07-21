package me.minidigger.globalmute.backend;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import me.minidigger.globalmute.backend.repository.UserRepository;
import me.minidigger.globalmute.model.User;

@Component
public class StartupListener {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public StartupListener(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        userRepository.save(new User("MiniDigger", "admin@minidigger.me", passwordEncoder.encode("secure")));
    }
}