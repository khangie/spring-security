package com.example.demo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    // This section configures which URLs need to be authenticated or not
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
}
