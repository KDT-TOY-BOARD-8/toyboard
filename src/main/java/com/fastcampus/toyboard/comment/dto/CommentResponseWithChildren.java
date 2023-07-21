package com.fastcampus.toyboard.comment.dto;

import com.fastcampus.toyboard.comment.model.Comment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Setter
@Getter
public class CommentResponseWithChildren {
  private final Long commentId;
  private final String nickname;
  private final String content;
  private final List<ChildCommentResponse> childComments;

  public static CommentResponseWithChildren of(Comment comment) {
    return new CommentResponseWithChildren(
        comment.getCommentId(),
        comment.getBoardUser().getNickname(),
        comment.getContent(),
        comment.getChildComments().stream()
            .map(ChildCommentResponse::of)
            .collect(Collectors.toList()));
  }
}
