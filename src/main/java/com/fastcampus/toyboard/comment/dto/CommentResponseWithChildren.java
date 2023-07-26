package com.fastcampus.toyboard.comment.dto;

import com.fastcampus.toyboard.comment.model.Comment;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Setter
@Getter
public class CommentResponseWithChildren {
  private final Long commentId;
  private final Long userId;
  private final String nickname;
  private final String content;
  private final List<ChildCommentResponse> childComments;

  public static CommentResponseWithChildren of(Comment comment) {
    return new CommentResponseWithChildren(
        comment.getCommentId(),
        comment.getBoardUser().getUserId(),
        comment.getBoardUser().getNickname(),
        comment.getContent(),
        comment.getChildComments() == null
            ? new ArrayList<>()
            : comment.getChildComments().stream()
                .map(ChildCommentResponse::of)
                .collect(Collectors.toList()));
  }
}
