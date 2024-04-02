package com.example.academiacx.security;

import com.example.academiacx.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomBasicAuthFilter customBasicAuthFilter;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        authorizeConfig -> {
                            authorizeConfig.requestMatchers("/public").permitAll();
                            //authorizeConfig.requestMatchers("/users").permitAll();
                            authorizeConfig.requestMatchers("/logout").permitAll();
                            authorizeConfig.requestMatchers(request -> {
                                String path = request.getServletPath();
                                return path.equals("/public") || path.startsWith("/users");
                            }).permitAll();
                            authorizeConfig.anyRequest().authenticated();
                        })
                .httpBasic(Customizer.withDefaults()) // Enable basic authentication
                .addFilterBefore(customBasicAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}