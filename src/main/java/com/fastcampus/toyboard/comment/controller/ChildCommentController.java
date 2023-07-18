package com.fastcampus.toyboard.comment.controller;

import com.fastcampus.toyboard.comment.dto.CommentRequestDto;
import com.fastcampus.toyboard.comment.service.ChildCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/child-comments")
@RequiredArgsConstructor
public class ChildCommentController {
  private final ChildCommentService childCommentService;

  @PostMapping("/new")
  public String postNewChildComment(
      //      @AuthenticationPrincipal BoardPrincipal boardPrincipal,
      CommentRequestDto commentRequestDto) {
    //    commentService.saveComment(CommentRequest.toDto(boardPrincipal.toDto()));

    // TODO - 수현님 작업 결과에 맞춰서 수정
    return "redirect:/board/";
    //           + CommentRequest.boardId();
  }

  @PostMapping("/{childCommentId}/delete")
  public String deleteChildComment(
      @PathVariable Long chlidCommentId,
      //      @AuthenticationPrincipal BoardPrincipal boardPrincipal,
      Long commentId) {
//      childCommentService.deleteChildComment(childCommentId,boardPrincipal.getNickname(), commentId);

    return "redirect:/board/";
    //    + articleId;
  }
}
