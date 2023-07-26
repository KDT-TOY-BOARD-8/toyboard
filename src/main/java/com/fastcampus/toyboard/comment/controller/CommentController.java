package com.fastcampus.toyboard.comment.controller;

import com.fastcampus.toyboard.comment.dto.CommentRequestDto;
import com.fastcampus.toyboard.comment.service.CommentService;
import com.fastcampus.toyboard.user.model.BoardUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CommentController {
  private final CommentService commentService;

  @PostMapping("/board/{category}/{boardId}/comment")
  public String postNewComment(
      @AuthenticationPrincipal BoardUser boardUser,
      @PathVariable String category,
      @PathVariable Long boardId,
      CommentRequestDto commentRequestDto) {
    log.info("댓글 작성 요청 : {}", commentRequestDto.getCommentContent());

    commentService.saveComment(boardId, boardUser.getUserId(), commentRequestDto);

    return "redirect:/board/" + category + "/" + boardId;
  }

  @DeleteMapping("/board/{category}/{boardId}/comment/{commentId}")
  public String deleteComment(
      @AuthenticationPrincipal BoardUser boardUser,
      @PathVariable String category,
      @PathVariable Long boardId,
      @PathVariable Long commentId) {
    commentService.deleteCommentByBoardIdByCommentId(commentId, boardUser.getUserId());

    return "redirect:/board/" + category + "/" + boardId;
  }
}
