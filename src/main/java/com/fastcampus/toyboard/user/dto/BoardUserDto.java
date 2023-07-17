package com.fastcampus.toyboard.user.dto;

import com.fastcampus.toyboard.user.model.BoardAuthority;
import com.fastcampus.toyboard.user.model.BoardUser;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class BoardUserDto {
  private Long userId;
  private String username;
  @Setter private String password;
  @Setter private String email;
  @Setter private String nickname;
  @Setter private Set<String> authorities;

  private BoardUserDto(String username, String password, String email, String nickName) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.nickname = nickName;
  }

  public static BoardUser toEntity(BoardUserDto userDto) {
    return BoardUser.of(
        userDto.getUserId(),
        userDto.getUsername(),
        userDto.getPassword(),
        userDto.getEmail(),
        userDto.getNickname(),
        userDto.getAuthorities().stream()
            .map(auth -> new BoardAuthority(userDto.getUserId(), auth))
            .collect(Collectors.toSet()));
  }

  public static BoardUser toEntity(BoardUserRequest.SignUpDto signUpDto) {
    return BoardUser.of(signUpDto);
  }

  public static BoardUserDto fromEntity(BoardUser boardUser) {
    return new BoardUserDto(
        boardUser.getUserId(),
        boardUser.getUsername(),
        boardUser.getPassword(),
        boardUser.getEmail(),
        boardUser.getNickname(),
        boardUser.getAuthorities() == null
            ? null
            : boardUser.getAuthorities().stream()
                .map(BoardAuthority::getAuthority)
                .collect(Collectors.toSet()));
  }

  public static BoardUserDto of(
      String username, String password, String email, String nickname, Set<String> authorities) {
    return new BoardUserDto(null, username, password, email, nickname, authorities);
  }

  public static BoardUserDto of(String username, String password, String email, String nickname) {
    return new BoardUserDto(null, username, password, email, nickname, null);
  }

  public static BoardUserDto of(BoardUserRequest.SignUpDto signUpDto) {
    return new BoardUserDto(
        signUpDto.getUsername(),
        signUpDto.getPassword(),
        signUpDto.getEmail(),
        signUpDto.getNickname());
  }
}
