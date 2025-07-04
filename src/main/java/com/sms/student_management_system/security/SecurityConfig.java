package com.sms.student_management_system.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.HttpSecurityDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(form -> form
                        .loginPage("/login")            // this must be your actual login form endpoint
                        .loginProcessingUrl("/do-login") // form action where Spring Security expects POST data
                        .defaultSuccessUrl("/index")    // after successful login
                        .permitAll()
                )
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/login", "/req/**", "/css/**", "/js/**").permitAll()
                        .anyRequest().authenticated()
                )
                .build();
    }
}
