package com.fastcampus.toyboard.user.dto;

import lombok.*;

public class UserRequest {
  @AllArgsConstructor
  @Getter
  @Setter
  @ToString
  public static class LoginDto {
    private final String username;
    private final String password;
  }

  @AllArgsConstructor
  @Getter
  @Setter
  @ToString
  public static class SignUpDto {
    private final String username;
    private final String password;
    private final String email;
    private final String nickname;
  }
}
