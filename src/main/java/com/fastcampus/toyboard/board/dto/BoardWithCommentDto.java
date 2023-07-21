package com.fastcampus.toyboard.board.dto;

import com.fastcampus.toyboard.comment.dto.CommentResponseDto;
import com.fastcampus.toyboard.user.dto.BoardUserDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardWithCommentDto {
  private final Long boardId;
  private final String title;
  private final String content;
  private final BoardUserDto boardUserDto;
  private final List<CommentResponseDto> comments; // 용호님 CommentDto
}
