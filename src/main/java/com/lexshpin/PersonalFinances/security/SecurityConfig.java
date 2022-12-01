package com.lexshpin.PersonalFinances.security;

import com.lexshpin.PersonalFinances.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userDetailsService;

    @Autowired
    public SecurityConfig(UserService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().disable()
                .csrf().disable()
                .httpBasic()
                .and()
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/index.html", "/", "/auth/login", "/auth/register").permitAll()
                        .requestMatchers("/dashboard", "/transactions").authenticated()
                        .anyRequest().permitAll())
                .userDetailsService(userDetailsService)
                .formLogin();
//                .loginPage("/auth/login")
//                .loginProcessingUrl("/auth/process_login")
//                .defaultSuccessUrl("/dashboard")
//                .failureUrl("/auth/login?error");

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
