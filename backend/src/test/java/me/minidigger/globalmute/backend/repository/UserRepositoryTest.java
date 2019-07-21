package me.minidigger.globalmute.backend.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import me.minidigger.globalmute.backend.repository.UserRepository;
import me.minidigger.globalmute.model.User;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() throws Exception {
        User user1 = new User("MiniDigger", "admin@minidigger.me", passwordEncoder.encode("secure"));
        User user2 = new User("Test", "test@minidigger.me", passwordEncoder.encode("notSoSecure"));
        assertNull(user1.getId());
        assertNull(user2.getId());
        this.userRepository.save(user1);
        this.userRepository.save(user2);
        assertNotNull(user1.getId());
        assertNotNull(user2.getId());
    }

    @Test
    void testFetchData() {
        /*Test data retrieval*/
        Optional<User> userA = userRepository.findByUsername("MiniDigger");
        assertTrue(userA.isPresent());
        assertEquals("admin@minidigger.me", userA.get().getEmail());
        /*Get all products, list should only have two*/
        List<User> users = (List<User>) userRepository.findAll();
        assertEquals(2, users.size());
    }
}