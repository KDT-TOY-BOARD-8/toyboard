package com.fastcampus.toyboard.board.controller;

import com.fastcampus.toyboard.user.model.BoardUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
  @PreAuthorize("hasAuthority('SPROUT')")
  @GetMapping("/sprout")
  public String boardSprout(@AuthenticationPrincipal BoardUser boardUser) {

    boardUser.getAuthorities().forEach(auth -> System.out.println(auth.getAuthority()));
    System.out.println(boardUser.getNickname());
    System.out.println();
    return "board/detail";
  }

  @PreAuthorize("hasAuthority('SPROUT')")
  @PostMapping("/sprout/new")
  public String postNewSproutBoard(@AuthenticationPrincipal BoardUser boardUser) {
    return "board/detail";
  }

  @PreAuthorize("hasAuthority('GREAT')")
  @GetMapping("/great")
  public String boardGreat() {
    return "board/detail";
  }

  @PreAuthorize("hasAuthority('GREAT')")
  @PostMapping("/great/new")
  public String postNewGreatBoard() {
    return "board/detail";
  }
}
