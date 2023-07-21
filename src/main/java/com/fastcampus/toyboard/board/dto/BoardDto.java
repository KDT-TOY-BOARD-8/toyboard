package com.fastcampus.toyboard.board.dto;

import com.fastcampus.toyboard.board.model.Board;
import com.fastcampus.toyboard.comment.dto.CommentDto;
import com.fastcampus.toyboard.user.dto.BoardUserDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class BoardDto {
  private Long boardId;
  private BoardUserDto boardUserDto;
  @Setter private String title;
  @Setter private String content;
  @Setter private String category;

  @Setter
  @JsonIgnoreProperties({"boardDto"})
  private List<CommentDto> commentDtos;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public static BoardDto of(
      BoardUserDto boarUserDto, String title, String content, String category) {
    return new BoardDto(null, boarUserDto, title, content, category, null, null, null);
  }

  public static Board toEntity(BoardDto dto) {
    return Board.of(
        BoardUserDto.toEntity(dto.getBoardUserDto()),
        dto.getTitle(),
        dto.getContent(),
        dto.getCategory());
  }

  public static BoardDto fromEntity(Board entity) {
    return new BoardDto(
        entity.getBoardId(),
        BoardUserDto.fromEntity(entity.getBoardUser()),
        entity.getTitle(),
        entity.getContent(),
        entity.getCategory(),
        entity.getComments().stream().map(CommentDto::fromEntity).collect(Collectors.toList()),
        entity.getCreatedAt(),
        entity.getUpdatedAt());
  }
}
