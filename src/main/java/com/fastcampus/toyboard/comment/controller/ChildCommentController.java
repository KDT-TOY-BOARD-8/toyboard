package com.fastcampus.toyboard.comment.controller;

import com.fastcampus.toyboard.comment.dto.ChildCommentRequestDto;
import com.fastcampus.toyboard.comment.service.ChildCommentService;
import com.fastcampus.toyboard.user.model.BoardUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ChildCommentController {
  private final ChildCommentService childCommentService;

  @PostMapping("/board/{category}/{boardId}/comment/{parentCommentId}/child-comment")
  public String postNewChildComment(
      @AuthenticationPrincipal BoardUser boardUser,
      @PathVariable String category,
      @PathVariable Long boardId,
      @PathVariable Long parentCommentId,
      ChildCommentRequestDto childCommentRequestDto) {

    childCommentService.saveNewChildComment(
        boardUser.getUserId(), parentCommentId, childCommentRequestDto);

    return "redirect:/board/" + category + "/" + boardId;
  }

  @DeleteMapping(
      "/board/{category}/{boardId}/comment/{parentCommentId}/child-comment/{childCommentId}")
  public String deleteChildComment(
      @PathVariable String category,
      @PathVariable Long boardId,
      @PathVariable Long parentCommentId,
      @PathVariable Long childCommentId) {

    childCommentService.deleteChildCommentById(parentCommentId, childCommentId);

    return "redirect:/board/" + category + "/" + boardId;
  }
}
