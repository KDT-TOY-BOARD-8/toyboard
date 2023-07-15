package com.fastcampus.toyboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf().disable();

    http.authorizeHttpRequests(
        auth ->
            auth.antMatchers("/assets/**")
                .permitAll()
                .antMatchers("/", "/login", "/sign-up")
                .permitAll()
                .anyRequest()
                .authenticated());

    http.formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/", false)
        .and()
        .logout(logout -> logout.logoutUrl("/logout"));

    return http.build();
  }
}
