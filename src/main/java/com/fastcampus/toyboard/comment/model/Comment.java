package com.fastcampus.toyboard.comment.model;

import com.fastcampus.toyboard.board.model.Board;
import com.fastcampus.toyboard.common.BaseTimeEntity;
import com.fastcampus.toyboard.user.domain.User;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "comment")
@NoArgsConstructor
@Getter
public class Comment extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String content;

  @OneToMany(mappedBy = "comment")
  private List<ChildComment> childComments;

  @ManyToOne private Board board;

  @ManyToOne private User user;
}
