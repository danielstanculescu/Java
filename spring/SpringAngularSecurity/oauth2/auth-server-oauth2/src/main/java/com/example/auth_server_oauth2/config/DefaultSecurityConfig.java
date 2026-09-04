package com.example.auth_server_oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class DefaultSecurityConfig {

    @Bean
    @Order(2) // 🟢 Executes AFTER the Authorization Server filter chain
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(authorize -> authorize
                        // ⚠️ Ensure your login page or custom error pages are permitted
                        //.requestMatchers("/login", "/error").permitAll()

                        .anyRequest().authenticated()
                        //.anyRequest().permitAll()
                )

                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // 🟢 Configures a mock test user account for your Postman / login tests
        UserDetails userDetails = User.withUsername("d")
                .password("{noop}p") // {noop} means plain-text password storage for testing
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(userDetails);
    }

}

