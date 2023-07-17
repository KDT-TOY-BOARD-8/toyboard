package com.fastcampus.toyboard.config.security;

import java.util.Collection;

import com.fastcampus.toyboard.user.model.BoardUser;
import lombok.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class BoardUserToken implements Authentication {
  @Getter private final String username;
  @Getter private final String credentials;
  @Getter @Setter private BoardUser principal;
  @Getter private Boolean authenticated;

  private BoardUserToken(String username, String password) {
    this.username = username;
    this.credentials = password;
    this.principal = new BoardUser();
    this.authenticated = false;
  }

  public static BoardUserToken of(String username, String credentials) {
    return new BoardUserToken(username, credentials);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return principal.getAuthorities();
  }

  @Override
  public Object getDetails() {
    return principal;
  }

  @Override
  public boolean isAuthenticated() {
    return authenticated;
  }

  @Override
  public String getName() {
    return username;
  }

  @Override
  public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    this.authenticated = isAuthenticated;
  }
}
