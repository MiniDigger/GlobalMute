package me.minidigger.globalmute.backend.util;

import org.junit.jupiter.api.Test;

import io.jsonwebtoken.Claims;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TokenUtilTest {

    @Test
    void test() {
        String token = TokenUtil.createToken("MiniDigger");
        System.out.println(token);
        assertThat(token, notNullValue());
        Claims claims = TokenUtil.parseToken(token);
        assertThat(claims, notNullValue());
        assertThat(claims.getSubject(), is("MiniDigger"));
        assertThat(claims.get("roles"), is("user"));
    }
}
