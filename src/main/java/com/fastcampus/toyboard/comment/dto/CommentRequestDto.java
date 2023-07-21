package com.fastcampus.toyboard.comment.dto;

import com.fastcampus.toyboard.board.dto.BoardDto;
import com.fastcampus.toyboard.board.model.Board;
import com.fastcampus.toyboard.user.dto.BoardUserDto;
import com.fastcampus.toyboard.user.model.BoardUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class CommentRequestDto {
  private final String commentContent;

  public static CommentRequestDto of(String content) {
    return new CommentRequestDto(content);
  }

  public CommentDto of(Board board, BoardUser boardUser) {
    return CommentDto.of(
        null, commentContent, null, BoardDto.fromEntity(board), BoardUserDto.fromEntity(boardUser));
  }
}
