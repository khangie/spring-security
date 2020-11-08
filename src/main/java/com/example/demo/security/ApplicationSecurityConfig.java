package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    // Configures which URLs need to be authenticated or not
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() // Authorize requests
                .antMatchers("/", "index", "/css/*", "/js/*")
                .permitAll() // Whitelist all pages that match the previous antMatchers
                .anyRequest()
                .authenticated() // Any request must be authenticated with username and password
                .and()
                .httpBasic(); // Use basic authentication
    }

    @Override
    @Bean
    // Defines users
    protected UserDetailsService userDetailsService() {
        UserDetails annaSmithUser = User.builder()
                .username("annasmith")
                .password(passwordEncoder.encode("password"))
                .roles("STUDENT") // Defined in Spring as ROLE_STUDENT
                .build();

        return new InMemoryUserDetailsManager(
                annaSmithUser
        );
    }
}
