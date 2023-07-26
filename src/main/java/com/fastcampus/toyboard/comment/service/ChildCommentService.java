package com.fastcampus.toyboard.comment.service;

import com.fastcampus.toyboard.comment.dto.ChildCommentRequestDto;
import com.fastcampus.toyboard.comment.model.ChildComment;
import com.fastcampus.toyboard.comment.model.Comment;
import com.fastcampus.toyboard.comment.repository.ChildCommentRepository;
import com.fastcampus.toyboard.comment.repository.CommentRepository;
import com.fastcampus.toyboard.user.repository.BoardUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

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

  public void deleteChildCommentById(Long parentCommentId, Long childCommentId) {
    Comment comment =
        commentRepository
            .findById(parentCommentId)
            .orElseThrow(
                () -> {
                  throw new IllegalArgumentException("원댓글이 존재하지 않습니다.");
                });

    comment.setChildComments(
        comment.getChildComments().stream()
            .filter(childComment -> !childComment.getChildCommentId().equals(childCommentId))
            .collect(Collectors.toList()));

    commentRepository.save(comment);

    childCommentRepository.deleteById(childCommentId);
  }
}
