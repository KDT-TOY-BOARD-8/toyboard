package com.fastcampus.toyboard.comment.controller;

import com.fastcampus.toyboard.comment.dto.ChildCommentRequestDto;
import com.fastcampus.toyboard.comment.dto.CommentRequestDto;
import com.fastcampus.toyboard.comment.service.ChildCommentService;
import com.fastcampus.toyboard.user.model.BoardUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class ChildCommentController {
  private final ChildCommentService childCommentService;

  @PostMapping("/board/{boardId}/comment/{parentCommentId}/child-comment/new")
  public String postNewChildComment(
      @AuthenticationPrincipal BoardUser boardUser,
      @PathVariable Long boardId,
      @PathVariable Long parentCommentId,
      ChildCommentRequestDto childCommentRequestDto) {

    childCommentService.saveNewChildComment(
        boardUser.getUserId(), parentCommentId, childCommentRequestDto);

    return "redirect:/board/" + boardId;
  }

  @DeleteMapping("/board/{boardId}/child-comment/{childCommentId}")
  public String deleteChildComment(@PathVariable Long boardId, @PathVariable Long childCommentId) {

    childCommentService.deleteChildCommentById(childCommentId);

    return "redirect:/board/" + boardId;
  }
}
