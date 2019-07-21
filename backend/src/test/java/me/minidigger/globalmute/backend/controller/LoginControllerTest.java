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

import io.jsonwebtoken.Claims;
import me.minidigger.globalmute.backend.repository.UserRepository;
import me.minidigger.globalmute.backend.util.TokenUtil;
import me.minidigger.globalmute.backend.util.TokenUtilTest;
import me.minidigger.globalmute.model.User;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setUp() throws Exception {
        Mockito.when(userRepository.findByUsername("MiniDigger"))
                .thenReturn(Optional.of(new User("MiniDigger", "admin@minidigger.me", passwordEncoder.encode("secure"))));
    }

    @Test
    void testValidLogin() throws Exception {
        MvcResult result = mvc.perform(post("/api/login")
                .content("{\"username\":\"MiniDigger\",\"password\":\"secure\"}")
                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        String json = result.getResponse().getContentAsString();
        String token = JsonPath.compile("$.token").read(json);
        Claims claims = TokenUtil.parseToken(token);
        assertThat(claims.getSubject(), is("MiniDigger"));
        assertThat(claims.get("roles"), is("user"));
    }

    @Test
    void testInvalidLogin() throws Exception {
        mvc.perform(post("/api/login")
                .content("{\"username\":\"MiniDigger\",\"password\":\"notSecure\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(400));
    }

    @Test
    void testInvalidUser() throws Exception {
        mvc.perform(post("/api/login")
                .content("{\"username\":\"NotMiniDigger\",\"password\":\"notSecure\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(400));
    }
}
