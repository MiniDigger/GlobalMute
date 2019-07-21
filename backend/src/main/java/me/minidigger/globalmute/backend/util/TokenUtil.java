package me.minidigger.globalmute.backend.util;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import me.minidigger.globalmute.backend.SecurityConfig;

public class TokenUtil {

    public static Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(SecurityConfig.JWT_TOKEN).parseClaimsJws(token).getBody();
    }

    public static String createToken(String username) {
        return Jwts.builder().setSubject(username).claim("roles", "user").setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, SecurityConfig.JWT_TOKEN).compact();
    }
}
