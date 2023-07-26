package com.fastcampus.toyboard.comment.model;

import com.fastcampus.toyboard.board.model.Board;
import com.fastcampus.toyboard.common.jpa.BaseTimeEntity;
import com.fastcampus.toyboard.user.model.BoardUser;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Entity(name = "comment_tb")
@NoArgsConstructor
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Comment extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long commentId;

  @Setter @Column private String content;

  @Setter
  @OneToMany(mappedBy = "parentComment", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<ChildComment> childComments;

  @ManyToOne
  @JoinColumn(name = "board_id")
  private Board board;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private BoardUser boardUser;

  private Comment(BoardUser boardUser, Board board, String content) {
    this.boardUser = boardUser;
    this.board = board;
    this.content = content;
  }

  public static Comment of(
      Long commentId,
      String content,
      List<ChildComment> childComments,
      Board board,
      BoardUser boardUser) {
    return new Comment(commentId, content, childComments, board, boardUser);
  }

  public static Comment of(BoardUser boardUser, Board board, String content) {
    return new Comment(boardUser, board, content);
  }
}
