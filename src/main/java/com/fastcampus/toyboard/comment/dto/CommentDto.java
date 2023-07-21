package com.fastcampus.toyboard.comment.dto;

import com.fastcampus.toyboard.board.dto.BoardDto;
import com.fastcampus.toyboard.comment.model.ChildComment;
import com.fastcampus.toyboard.comment.model.Comment;
import com.fastcampus.toyboard.user.dto.BoardUserDto;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CommentDto {
  private Long commentId;
  @Setter private String content;

  @Setter
  @JsonIgnoreProperties({"commentDto"})
  private List<ChildCommentDto> childCommentDtos;

  private BoardDto boardDto;
  private BoardUserDto boardUserDto;

  public static CommentDto of(
      Long commentId,
      String content,
      List<ChildCommentDto> childCommentDtos,
      BoardDto boardDto,
      BoardUserDto boardUserDto) {
    return new CommentDto(commentId, content, childCommentDtos, boardDto, boardUserDto);
  }

  public static CommentDto fromEntity(Comment comment) {
    return new CommentDto(
        comment.getCommentId(),
        comment.getContent(),
        comment.getChildComments().stream()
            .map(ChildCommentDto::fromEntity)
            .collect(Collectors.toList()),
        BoardDto.fromEntity(comment.getBoard()),
        BoardUserDto.fromEntity(comment.getBoardUser()));
  }

  public static Comment toEntity(CommentDto commentDto, List<ChildComment> childComments) {
    return Comment.of(
        commentDto.getCommentId(),
        commentDto.getContent(),
        childComments,
        BoardDto.toEntity(commentDto.getBoardDto()),
        BoardUserDto.toEntity(commentDto.getBoardUserDto()));
  }

  public static Comment toEntity(CommentDto commentDto) {
    return Comment.of(
        commentDto.getCommentId(),
        commentDto.getContent(),
        null,
        BoardDto.toEntity(commentDto.getBoardDto()),
        BoardUserDto.toEntity(commentDto.getBoardUserDto()));
  }
}
