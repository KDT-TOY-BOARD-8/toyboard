package com.fastcampus.toyboard.config.security;

import com.fastcampus.toyboard.user.repository.BoardUserRepository;
import com.fastcampus.toyboard.user.service.BoardUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
  private final BoardUserRepository boardUserRepository;
  private final PasswordEncoder passwordEncoder;

  @Bean
  public RoleHierarchy roleHierarchy() {
    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
    roleHierarchy.setHierarchy("ADMIN > GREAT > SPROUT > BLACK");
    return roleHierarchy;
  }

  @Bean
  public UserDetailsService userDetailsService(BoardUserService boardUserService) {
    return boardUserService;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.httpBasic().disable();

    http.authorizeHttpRequests(
        auth ->
            auth.antMatchers("/assets", "/assets/**")
                .permitAll()
                .antMatchers("/", "/login", "/sign-up", "/login-failed")
                .permitAll()
                .antMatchers("/board", "/board/**")
                .hasAnyAuthority("SPROUT", "GREAT", "ADMIN")
                .anyRequest()
                .authenticated());

    http.formLogin().disable();

    http.addFilterAt(
        new BoardUserAuthenticationFilter(
            new BoardUserManager(boardUserRepository, passwordEncoder)),
        UsernamePasswordAuthenticationFilter.class);

    http.logout(
        logout -> logout.logoutUrl("/logout").deleteCookies("JSESSIONID").logoutSuccessUrl("/"));

    http.exceptionHandling().accessDeniedPage("/error403");

    return http.build();
  }

  @Bean
  public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
    return new HiddenHttpMethodFilter();
  }
}
