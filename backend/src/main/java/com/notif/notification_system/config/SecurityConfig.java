package com.notif.notification_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .cors()
      .and()
      .csrf().disable()
      .authorizeHttpRequests()
      .requestMatchers("/api/users/**", "/api/logs/**", "/api/messages/**",  "/api/catalog/**" ).permitAll()
      .anyRequest().authenticated(); // ajuste conforme seu caso

    return http.build();
  }

}
