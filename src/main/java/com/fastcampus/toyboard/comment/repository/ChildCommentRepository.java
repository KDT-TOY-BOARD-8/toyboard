package com.fastcampus.toyboard.comment.repository;

import com.fastcampus.toyboard.comment.model.ChildComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChildCommentRepository extends JpaRepository<ChildComment, Long> {
  List<ChildComment> findChildCommentsByComment_Id(Long commentId);
}
