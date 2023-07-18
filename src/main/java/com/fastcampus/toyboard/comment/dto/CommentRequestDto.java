package com.fastcampus.toyboard.comment.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentRequestDto {
  private String content;
  private Long boardId;
  private String nickName;

  public static CommentRequestDto of(String content, Long boardId, String nickName) {
    return new CommentRequestDto(content, boardId, nickName);
  }
}
