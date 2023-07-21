package com.fastcampus.toyboard.comment.service;

import com.fastcampus.toyboard.comment.dto.ChildCommentRequestDto;
import com.fastcampus.toyboard.comment.model.ChildComment;
import com.fastcampus.toyboard.comment.repository.ChildCommentRepository;
import com.fastcampus.toyboard.comment.repository.CommentRepository;
import com.fastcampus.toyboard.user.repository.BoardUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ChildCommentService {
  private final CommentRepository commentRepository;
  private final ChildCommentRepository childCommentRepository;
  private final BoardUserRepository boardUserRepository;

  public void saveNewChildComment(
      Long boardUserId, Long parentCommentId, ChildCommentRequestDto childCommentRequestDto) {
    childCommentRepository.save(
        ChildComment.of(
            null,
            childCommentRequestDto.getChildCommentContent(),
            commentRepository
                .findById(parentCommentId)
                .orElseThrow(
                    () -> {
                      throw new RuntimeException("Can't find parent comment.");
                    }),
            boardUserRepository
                .findById(boardUserId)
                .orElseThrow(
                    () -> {
                      throw new RuntimeException("Can't find user.");
                    })));
  }

  public void deleteChildCommentById(Long childCommentId) {
    childCommentRepository.deleteById(childCommentId);
  }
}
