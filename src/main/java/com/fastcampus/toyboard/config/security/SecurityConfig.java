package com.fastcampus.toyboard.config.security;

import com.fastcampus.toyboard.user.repository.BoardUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
  private final BoardUserRepository boardUserRepository;
  private final PasswordEncoder passwordEncoder;

  @Bean
  public RoleHierarchy roleHierarchy() {
    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
    roleHierarchy.setHierarchy("ROLE_GREAT > ROLE_SPROUT");
    return roleHierarchy;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.httpBasic().disable();

    http.authorizeHttpRequests(
        auth ->
            auth.antMatchers(
                    "/assets",
                    "/assets/**",
                    "/css",
                    "/css/**",
                    "/img",
                    "/img/**",
                    "/js",
                    "/js/**",
                    "/vendor",
                    "/vendor/**",
                    "/scss",
                    "/scss/**")
                .permitAll()
                .antMatchers("/", "/login", "/sign-up", "/sign-up/**", "/user/**")
                .permitAll()
                .anyRequest()
                .authenticated());

    http.addFilterAt(
            new BoardUserAuthenticationFilter(
                new BoardUserManager(boardUserRepository, passwordEncoder)),
            UsernamePasswordAuthenticationFilter.class)
        .formLogin(login -> login.loginPage("/login").defaultSuccessUrl("/", false).permitAll());

    http.logout(
        logout -> logout.logoutUrl("/logout").deleteCookies("JSESSIONID").logoutSuccessUrl("/"));
    return http.build();
  }
}
