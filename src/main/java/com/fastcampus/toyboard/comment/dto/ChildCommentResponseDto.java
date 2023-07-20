package com.fastcampus.toyboard.comment.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class ChildCommentResponseDto {
  private Long childCommentId;

  private String content;

  private Long commentId;

  private String nickname;

  public static ChildCommentResponseDto of(
      Long id, String content, Long commentId, String nickname) {
    return new ChildCommentResponseDto(id, content, commentId, nickname);
  }

  public static ChildCommentResponseDto of(ChildCommentDto childCommentDto) {
    return new ChildCommentResponseDto(
        childCommentDto.getChildCommentId(),
        childCommentDto.getContent(),
        childCommentDto.getCommentDto().getCommentId(),
        childCommentDto.getBoardUserDto().getNickname());
  }
}
