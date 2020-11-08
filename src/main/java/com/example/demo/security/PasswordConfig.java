package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

    @Bean
    // BCryptPasswordEncoder is the most populate password encoder
    public PasswordEncoder  passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}
