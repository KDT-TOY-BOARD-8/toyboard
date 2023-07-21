package com.fastcampus.toyboard.board.dto;

import com.fastcampus.toyboard.user.dto.BoardUserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BoardRequestDto {
  private final String title;
  private final String content;

  public static BoardRequestDto of(String title, String content) {
    return new BoardRequestDto(title, content);
  }

  public BoardDto toBoardDto(BoardUserDto boardUserDto, String category) {
    return BoardDto.of(boardUserDto, this.title, this.content, category);
  }
}
