package com.notif.notification_system;
import java.util.List;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@TestConfiguration
public class TestSecurityConfig {

  @Bean
  @Primary
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       http
      .cors().and()
      .csrf().disable()
      .authorizeHttpRequests()
      .requestMatchers("/api/users/**", "/api/logs/**", "/api/messages/**").permitAll() 
      .anyRequest().authenticated();  // demais rotas precisam de autenticação

    return http.build();
  }

  @Bean
public CorsFilter corsFilter() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(List.of("http://127.0.0.1:5500")); 
    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    config.setAllowedHeaders(List.of("*"));
    config.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
}
}

