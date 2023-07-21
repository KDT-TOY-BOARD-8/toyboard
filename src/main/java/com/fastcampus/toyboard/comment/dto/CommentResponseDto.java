package com.fastcampus.toyboard.comment.dto;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Getter
public class CommentResponseDto {
  private Long commentId;
  private String content;
  private Long boardId;
  private String nickname;

  private List<ChildCommentResponse> childCommentDtos;

  public static CommentResponseDto of(Long id, String content, Long boardId, String nickName) {
    return new CommentResponseDto(id, content, boardId, nickName, null);
  }

  public static CommentResponseDto of(CommentDto commentDto) {
    return new CommentResponseDto(
        commentDto.getCommentId(),
        commentDto.getContent(),
        commentDto.getBoardDto().getBoardId(),
        commentDto.getBoardUserDto().getNickname(),
        commentDto.getChildCommentDtos().stream()
            .map(ChildCommentResponse::of)
            .collect(Collectors.toList()));
  }
}
