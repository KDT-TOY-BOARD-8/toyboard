package com.fastcampus.toyboard.user.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "board_authority_tb")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@IdClass(BoardAuthority.class)
public class BoardAuthority implements GrantedAuthority {
  @Id
  @Column(name = "user_id")
  Long userId;

  @Id String authority;
}
