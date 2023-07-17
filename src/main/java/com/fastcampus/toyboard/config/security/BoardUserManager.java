package com.fastcampus.toyboard.config.security;

import com.fastcampus.toyboard.user.repository.BoardUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class BoardUserManager implements AuthenticationManager {
  private final BoardUserRepository boardUserRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    if (!(authentication instanceof BoardUserToken)) {
      throw new AuthenticationException("Token Type Error.") {
        @Override
        public String getMessage() {
          return super.getMessage();
        }
      };
    }

    BoardUserToken token = (BoardUserToken) authentication;

    boardUserRepository
        .findBoardUserByUsername(token.getName())
        .ifPresent(
            user -> {
              System.out.println(
                  "Token Password : "
                      + passwordEncoder.encode(token.getCredentials())
                      + ", DB Password : "
                      + user.getPassword());
              if (!passwordEncoder.matches(token.getCredentials(), user.getPassword()))
                throw new AuthenticationException("Authentication Failed.") {
                  @Override
                  public String getMessage() {
                    return super.getMessage();
                  }
                };
            });

    token.setPrincipal(
        boardUserRepository
            .findBoardUserByUsername(token.getUsername())
            .orElseThrow(
                () -> {
                  throw new AuthenticationException("Authentication Failed.") {
                    @Override
                    public String getMessage() {
                      return super.getMessage();
                    }
                  };
                }));

    token.setAuthenticated(true);

    return token;
  }
}
