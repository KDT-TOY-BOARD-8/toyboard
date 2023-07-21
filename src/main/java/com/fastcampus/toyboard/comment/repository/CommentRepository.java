package com.fastcampus.toyboard.comment.repository;

import com.fastcampus.toyboard.comment.model.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  List<Comment> findCommentsByBoard_BoardId(Long boardId);
}
