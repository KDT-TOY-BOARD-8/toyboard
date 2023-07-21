package com.fastcampus.toyboard.comment.dto;

import com.fastcampus.toyboard.comment.model.ChildComment;
import com.fastcampus.toyboard.comment.model.Comment;
import com.fastcampus.toyboard.user.dto.BoardUserDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ChildCommentDto {
  private Long childCommentId;
  @Setter private String content;

  private Long parentCommentId;
  private BoardUserDto boardUserDto;

  public static ChildCommentDto fromEntity(ChildComment childComment) {
    return new ChildCommentDto(
        childComment.getChildCommentId(),
        childComment.getContent(),
        childComment.getParentComment().getCommentId(),
        BoardUserDto.fromEntity(childComment.getBoardUser()));
  }

  public static ChildComment toEntity(ChildCommentDto childCommentDto, Comment comment) {
    return ChildComment.of(
        childCommentDto.getChildCommentId(),
        childCommentDto.getContent(),
        comment,
        BoardUserDto.toEntity(childCommentDto.getBoardUserDto()));
  }
}
