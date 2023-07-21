package com.fastcampus.toyboard.board.repository;

import com.fastcampus.toyboard.user.model.BoardUser;
import java.time.LocalDateTime;

public interface IBoard {
  Long getBoardId();

  String getNickname();

  String getTitle();

  String getContent();

  String getCategory();

  Boolean getHide();

  LocalDateTime getCreatedAt();
}
