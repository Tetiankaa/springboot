package com.example.springboot.security;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@AllArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable) //TOKEN
                .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorization->authorization
                        .requestMatchers(HttpMethod.POST,"/clients/save","/clients/login").permitAll()
                        .requestMatchers(HttpMethod.GET,"/clients/**").permitAll()
                        .requestMatchers("/admin/**").hasAnyAuthority("ADMIN,SUPERADMIN")
                        .requestMatchers("/superadmin/**").hasAuthority("SUPERADMIN"));
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
//CSRF (Cross-Site Request Forgery) is a type of web security vulnerability that allows an attacker
// to perform unwanted actions on behalf of an authenticated user. It occurs when a malicious website
// tricks a user's browser into making a request to another website where the user is authenticated.
//A CSRF token is a unique and unpredictable value that is associated with a user's session.
//By default, Spring Security enables CSRF protection to prevent CSRF attacks.

//sessionManagement - refers to how the server handles and manages user sessions. A session is a way
// to maintain stateful information about a user across multiple requests. It allows the server to
// recognize and identify the user, store user-specific data, and provide personalized functionality.
