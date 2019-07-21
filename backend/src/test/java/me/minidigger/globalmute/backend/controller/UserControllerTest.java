package me.minidigger.globalmute.backend.controller;

import com.jayway.jsonpath.JsonPath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;
import java.util.UUID;

import me.minidigger.globalmute.backend.repository.UserRepository;
import me.minidigger.globalmute.backend.util.TokenUtil;
import me.minidigger.globalmute.model.User;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
public class UserControllerTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MockMvc mvc;

    private User user;

    @BeforeEach
    void setUp() throws Exception {
        user = new User("MiniDigger", "admin@minidigger.me", passwordEncoder.encode("secure"));
        user.setId(UUID.randomUUID());
        Mockito.when(userRepository.findByUsername("MiniDigger"))
                .thenReturn(Optional.of(user));
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.ofNullable(user));
    }

    @Test
    void testGetUser() throws Exception {
        MvcResult result = mvc.perform(post("/api/secure/users/" + user.getId())
                .header("Authorization", "Bearer " + TokenUtil.createToken("MiniDigger"))
                .content("{\"username\":\"MiniDigger\",\"password\":\"secure\"}")
                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        String json = result.getResponse().getContentAsString();
        String token = JsonPath.compile("$.token").read(json);
    }
}
