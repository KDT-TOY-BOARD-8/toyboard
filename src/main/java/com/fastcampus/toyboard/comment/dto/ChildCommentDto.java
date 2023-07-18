package com.fastcampus.toyboard.comment.dto;

import com.fastcampus.toyboard.comment.model.ChildComment;
import com.fastcampus.toyboard.comment.model.Comment;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChildCommentDto {
  private Long id;

  private String content;

  private Long commentId;

  private String nickname;

  public static ChildCommentDto of(Long id, String content, Long commentId, String nickname) {
    return new ChildCommentDto(id, content, commentId, nickname);
  }

  public static ChildCommentDto fromEntity(ChildComment childComment) {
    return new ChildCommentDto(
        childComment.getId(),
        childComment.getContent(),
        childComment.getComment().getId(),
        childComment.getUser().getNickName());
  }
}
