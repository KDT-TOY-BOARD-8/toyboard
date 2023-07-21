package com.fastcampus.toyboard.board.dto;

import com.fastcampus.toyboard.board.model.Board;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class BoardResponseDto {
  private Long boardId;
  private String nickname;
  private String title;
  private String content;
  private String category;
  private LocalDateTime createdAt;

  public static BoardResponseDto of(
      Long boardId,
      String nickname,
      String title,
      String content,
      String category,
      LocalDateTime createdAt) {
    return new BoardResponseDto(boardId, nickname, title, content, category, createdAt);
  }

  public static BoardResponseDto of(Board board) {
    return new BoardResponseDto(
        board.getBoardId(),
        board.getBoardUser().getNickname(),
        board.getTitle(),
        board.getContent(),
        board.getCategory(),
        board.getCreatedAt());
  }
}
