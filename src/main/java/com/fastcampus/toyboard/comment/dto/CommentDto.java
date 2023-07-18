package com.fastcampus.toyboard.comment.dto;

import com.fastcampus.toyboard.comment.model.Comment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentDto {
  private Long id;
  private String content;
  private List<ChildCommentDto> childCommentDtoList;
  private Long boardId;
  private String nickName;

  public static CommentDto of(
      Long id,
      String content,
      List<ChildCommentDto> childCommentDtos,
      Long boardId,
      String nickName) {
    return new CommentDto(id, content, childCommentDtos, boardId, nickName);
  }

  public static CommentDto fromEntity(Comment comment) {
    return new CommentDto(
        comment.getId(),
        comment.getContent(),
        comment.getChildComments().stream()
            .map(ChildCommentDto::fromEntity)
            .collect(Collectors.toList()),
        comment.getBoard().getId(),
        comment.getUser().getNickName());
  }
}
