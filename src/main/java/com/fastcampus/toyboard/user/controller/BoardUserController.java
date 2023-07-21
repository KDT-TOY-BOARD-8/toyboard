package com.fastcampus.toyboard.user.controller;

import com.fastcampus.toyboard.user.dto.BoardUserRequest;
import com.fastcampus.toyboard.user.service.BoardUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BoardUserController {
  private final BoardUserService boardUserService;

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/sign-up")
  public String signUp() {
    return "sign-up";
  }

  @PostMapping("/sign-up")
  public String register(BoardUserRequest.SignUpDto signUpDto) {
    System.out.println(boardUserService.signUp(signUpDto));

    return "redirect:/";
  }
}
