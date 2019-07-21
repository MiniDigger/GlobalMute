package me.minidigger.globalmute.backend;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import me.minidigger.globalmute.backend.repository.UserRepository;
import me.minidigger.globalmute.backend.service.UserService;
import me.minidigger.globalmute.backend.service.UserServiceImpl;

@TestConfiguration
public class TestContext {

    @MockBean
    private UserRepository userRepository;

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userRepository);
    }
}
