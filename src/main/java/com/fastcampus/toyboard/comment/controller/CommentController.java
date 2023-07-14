package com.fastcampus.toyboard.comment.controller;

import com.fastcampus.toyboard.comment.dto.CommentRequestDto;
import com.fastcampus.toyboard.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
  private final CommentService commentService;

  @PostMapping("/new")
  public String postNewComment(
      //      @AuthenticationPrincipal BoardPrincipal boardPrincipal,
      CommentRequestDto commentRequestDto) {
    //    commentService.saveComment(CommentRequest.toDto(boardPrincipal.toDto()));

    // TODO - 수현님 작업 결과에 맞춰서 수정
    return "redirect:/board/";
    //           + CommentRequest.boardId();
  }

  @PostMapping("/{commentId}/delete")
  public String deleteComment(
      @PathVariable Long commentId,
      //      @AuthenticationPrincipal BoardPrincipal boardPrincipal,
      Long articleId) {
    //    commentService.deleteComment(commentId, boardPrincipal.getUsername());

    return "redirect:/board/";
    //    + articleId;
  }
}
