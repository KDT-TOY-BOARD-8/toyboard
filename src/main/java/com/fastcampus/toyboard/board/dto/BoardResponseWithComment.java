package com.fastcampus.toyboard.board.dto;

import com.fastcampus.toyboard.board.model.Board;
import com.fastcampus.toyboard.comment.dto.CommentResponseWithChildren;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class BoardResponseWithComment {
  private Long boardId;
  private Long userId;
  private String nickname;
  private String title;
  private String content;
  private String category;
  private LocalDateTime createdAt;
  private List<CommentResponseWithChildren> comments;

  public static BoardResponseWithComment of(Board board) {
    return new BoardResponseWithComment(
        board.getBoardId(),
        board.getBoardUser().getUserId(),
        board.getBoardUser().getNickname(),
        board.getTitle(),
        board.getContent(),
        board.getCategory(),
        board.getCreatedAt(),
        board.getComments() == null
            ? new ArrayList<>()
            : board.getComments().stream()
                .map(CommentResponseWithChildren::of)
                .collect(Collectors.toList()));
  }
}
