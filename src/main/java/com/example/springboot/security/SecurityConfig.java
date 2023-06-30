package com.example.springboot.security;



import com.example.springboot.security.filter.JWTFilter;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.lang.reflect.Array;
import java.util.Arrays;

@AllArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private JWTFilter jwtFilter;

    @SneakyThrows
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable) //TOKEN
                .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorization->authorization
                        .requestMatchers(HttpMethod.POST,"/clients/save","/clients/login").permitAll()
                        .requestMatchers(HttpMethod.GET,"/clients/**").permitAll()
                        .requestMatchers("/admin/**").hasAnyAuthority("ADMIN,SUPERADMIN")
                        .requestMatchers("/superadmin/**").hasAuthority("SUPERADMIN"))
                .addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @SneakyThrows
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        return authenticationConfiguration.getAuthenticationManager();
    }// This bean is responsible for authenticating the users in the application.

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){ //CORS allows you to specify which origins, methods, and headers are allowed when making cross-origin requests
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedMethods(Arrays.asList("GET","POST"));
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000","http://localhost:4200"));
        corsConfiguration.addExposedHeader("Authorization");
        //Ця конфігурація дозволяє перехресні запити з http://localhost:3000 і http://localhost:4200 за допомогою методів GET і POST.
        // Заголовок «Авторизація» також буде відкритий, що дозволить клієнту отримати доступ до нього.

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);
// "/**"-indicating that all endpoints in your application should adhere to the CORS settings defined in the CorsConfiguration object.
        return source;
    }

    @Bean
    public UserDetailsService userDetailsService(){ //is used by Spring Security to load user details during the authentication process.
        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
        UserDetails superadmin = userBuilder.username("superadmin")
                .password("tanya")
                .roles("SUPERADMIN")
                .build();
        return new InMemoryUserDetailsManager(superadmin);
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
