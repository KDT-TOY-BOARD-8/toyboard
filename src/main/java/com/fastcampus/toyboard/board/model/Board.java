package com.fastcampus.toyboard.board.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "board_tb")
@Getter
@Setter
public class Board {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title; // 제목

  @Column(nullable = false)
  private String nickName; // 작성자

  @Column(columnDefinition = "TEXT", nullable = false)
  private String content; // 내용

  @Enumerated(EnumType.STRING)
  private BoardType boardType;
}
