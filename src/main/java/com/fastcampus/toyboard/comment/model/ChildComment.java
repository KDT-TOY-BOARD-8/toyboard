package com.fastcampus.toyboard.comment.model;

import com.fastcampus.toyboard.common.BaseTimeEntity;
import com.fastcampus.toyboard.user.model.BoardUser;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@Entity(name = "child_comment_tb")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public class ChildComment extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long childCommentId;

  @Column private String content;

  @ManyToOne
  @JoinColumn(name = "comment_id")
  private Comment parentComment;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private BoardUser boardUser;

  public static ChildComment of(
      Long childCommentId, String content, Comment comment, BoardUser boardUser) {
    return new ChildComment(childCommentId, content, comment, boardUser);
  }
}
