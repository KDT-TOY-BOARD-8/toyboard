package com.fastcampus.toyboard.comment.model;

import com.fastcampus.toyboard.user.model.User;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "child_comment")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
public class ChildComment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column String content;

  @ManyToOne Comment comment;

  @ManyToOne User user;

  @CreatedDate LocalDateTime createdAt;

  @LastModifiedDate LocalDateTime updatedAt;
}
