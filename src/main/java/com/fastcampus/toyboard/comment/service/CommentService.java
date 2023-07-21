package com.fastcampus.toyboard.comment.service;

import com.fastcampus.toyboard.board.dto.BoardDto;
import com.fastcampus.toyboard.board.repository.BoardRepository;
import com.fastcampus.toyboard.comment.dto.CommentRequestDto;
import com.fastcampus.toyboard.comment.dto.CommentResponseWithChildren;
import com.fastcampus.toyboard.comment.model.ChildComment;
import com.fastcampus.toyboard.comment.model.Comment;
import com.fastcampus.toyboard.comment.repository.ChildCommentRepository;
import com.fastcampus.toyboard.comment.repository.CommentRepository;
import java.util.List;
import java.util.stream.Collectors;

import com.fastcampus.toyboard.user.model.BoardUser;
import com.fastcampus.toyboard.user.repository.BoardUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
  private final CommentRepository commentRepository;
  private final BoardRepository boardRepository;
  private final BoardUserRepository boardUserRepository;
  private final ChildCommentRepository childCommentRepository;

  public List<CommentResponseWithChildren> getCommentsByBoardId(Long boardId) {

    return commentRepository.findCommentsByBoard_BoardId(boardId).stream()
        .map(CommentResponseWithChildren::of)
        .collect(Collectors.toList());
  }

  public void saveComment(Long boardId, Long userId, CommentRequestDto commentRequestDto) {
    commentRepository.save(
        Comment.of(
            null,
            commentRequestDto.getCommentContent(),
            null,
            boardRepository
                .findById(boardId)
                .orElseThrow(
                    () -> {
                      throw new RuntimeException("Can't find board.");
                    }),
            boardUserRepository
                .findById(userId)
                .orElseThrow(
                    () -> {
                      throw new RuntimeException("Can't find User.");
                    })));
  }

  public void deleteCommentByBoardIdByCommentId(Long commentId, Long boardUserId) {
    Comment comment =
        commentRepository
            .findById(commentId)
            .orElseThrow(
                () -> {
                  throw new RuntimeException("Can't find comment with id : " + commentId);
                });

    BoardUser boardUser =
        boardUserRepository
            .findById(boardUserId)
            .orElseThrow(
                () -> {
                  throw new RuntimeException("Can't find user.");
                });

    if (!boardUser.getNickname().equals(comment.getBoardUser().getNickname())) {
      throw new RuntimeException("작성자만 삭제할 수 있습니다.");
    }

    if (comment.getChildComments() != null) {
      for (ChildComment childComment : comment.getChildComments()) {
        childCommentRepository.deleteById(childComment.getChildCommentId());
      }
    }

    commentRepository.deleteById(commentId);
  }
}
