package com.fastcampus.toyboard.board.model;

import com.fastcampus.toyboard.comment.model.Comment;
import com.fastcampus.toyboard.common.BaseTimeEntity;
import com.fastcampus.toyboard.user.model.BoardUser;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import lombok.*;

@Entity(name = "board_tb")
@Getter
@NoArgsConstructor
public class Board extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long boardId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private BoardUser boardUser; // 작성자

  @Setter @Column private String title; // 제목

  @Setter @Column private String content; // 내용

  @Column private String category; // 게시판 카테고리

  @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
  private List<Comment> comments; // 댓글

  private Boolean hide;

  private Board(BoardUser boardUser, String title, String content, String category) {
    this.title = title;
    this.content = content;
    this.boardUser = boardUser;
    this.category = category;
    this.hide = false;
  }

  public static Board of(BoardUser boardUser, String title, String content, String category) {
    return new Board(boardUser, title, content, category);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Board board = (Board) o;

    return this.getBoardId() != null && this.getBoardId().equals(board.getBoardId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getBoardId());
  }
}
