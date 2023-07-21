package com.fastcampus.toyboard.comment.controller;

import com.fastcampus.toyboard.comment.dto.CommentRequestDto;
import com.fastcampus.toyboard.comment.service.CommentService;
import com.fastcampus.toyboard.user.model.BoardUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CommentController {
  private final CommentService commentService;

  @PostMapping("/board/{boardId}/comment/new")
  public String postNewComment(
      @AuthenticationPrincipal BoardUser boardUser,
      @PathVariable Long boardId,
      CommentRequestDto commentRequestDto) {
    System.out.println(commentRequestDto.getCommentContent());

    commentService.saveComment(boardId, boardUser.getUserId(), commentRequestDto);

    return "redirect:/board/" + boardId;
  }

  @DeleteMapping("/board/{boardId}/comment/{commentId}")
  public String deleteComment(
      @PathVariable Long boardId,
      @PathVariable Long commentId,
      @AuthenticationPrincipal BoardUser boardUser) {
    commentService.deleteCommentByBoardIdByCommentId(commentId, boardUser.getUserId());

    return "redirect:/board/" + boardId;
  }
}
