package me.minidigger.globalmute.backend;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import me.minidigger.globalmute.backend.filter.JwtFilter;

@Configuration
public class SecurityConfig {

    public static final String JWT_TOKEN = "geheim!";

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>(new JwtFilter());
        registrationBean.addUrlPatterns("/api/secure/*");
        return registrationBean;
    }
}