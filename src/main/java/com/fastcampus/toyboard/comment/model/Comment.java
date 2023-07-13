package com.fastcampus.toyboard.comment.model;

import com.fastcampus.toyboard.board.model.Board;
import com.fastcampus.toyboard.user.model.User;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "comment")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column String content;

  @OneToMany(mappedBy = "comment")
  List<ChildComment> childComments;

  @ManyToOne Board board;

  @ManyToOne User user;

  @CreatedDate LocalDateTime createdAt;

  @LastModifiedDate LocalDateTime updatedAt;
}
