package com.fastcampus.toyboard.comment.dto;

import com.fastcampus.toyboard.comment.model.ChildComment;
import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class ChildCommentResponse {
  private final Long parentCommentId;
  private final String content;
  private final Long userId;
  private final String nickname;

  public static ChildCommentResponse of(
      Long parentCommentId, String content, Long userId, String nickname) {
    return new ChildCommentResponse(parentCommentId, content, userId, nickname);
  }

  public static ChildCommentResponse of(ChildCommentDto childCommentDto) {
    return new ChildCommentResponse(
        childCommentDto.getParentCommentId(),
        childCommentDto.getContent(),
        childCommentDto.getBoardUserDto().getUserId(),
        childCommentDto.getBoardUserDto().getNickname());
  }

  public static ChildCommentResponse of(ChildComment childComment) {
    return new ChildCommentResponse(
        childComment.getParentComment().getCommentId(),
        childComment.getContent(),
        childComment.getBoardUser().getUserId(),
        childComment.getBoardUser().getNickname());
  }
}
