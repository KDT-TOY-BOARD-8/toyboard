package com.fastcampus.toyboard.user.model;

import com.fastcampus.toyboard.common.BaseTimeEntity;
import java.util.Set;
import javax.persistence.*;

import com.fastcampus.toyboard.user.dto.BoardUserDto;
import com.fastcampus.toyboard.user.dto.BoardUserRequest;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(
    name = "board_user_tb",
    indexes = {
      @Index(columnList = "username"),
      @Index(columnList = "email"),
      @Index(columnList = "nickname")
    })
public class BoardUser extends BaseTimeEntity implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  @Setter
  private String password;

  @Column(nullable = false, unique = true)
  @Setter
  private String email;

  @Column(nullable = false, unique = true)
  @Setter
  private String nickname;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "user_id"))
  @Setter
  private Set<BoardAuthority> authorities;

  private BoardUser(String username, String password, String email, String nickname) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.nickname = nickname;
  }

  public static BoardUser of(
      Long userId,
      String username,
      String password,
      String email,
      String nickname,
      Set<BoardAuthority> authorities) {
    return new BoardUser(userId, username, password, email, nickname, authorities);
  }

  public static BoardUser of(BoardUserDto boardUserDto) {
    return new BoardUser(
        boardUserDto.getUsername(),
        boardUserDto.getPassword(),
        boardUserDto.getEmail(),
        boardUserDto.getNickname());
  }

  public static BoardUser of(BoardUserRequest.SignUpDto signUpDto) {
    return new BoardUser(
        signUpDto.getUsername(),
        signUpDto.getPassword(),
        signUpDto.getEmail(),
        signUpDto.getNickname());
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
