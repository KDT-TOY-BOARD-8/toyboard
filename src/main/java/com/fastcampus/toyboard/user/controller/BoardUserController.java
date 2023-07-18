package com.fastcampus.toyboard.user.controller;

import com.fastcampus.toyboard.user.dto.BoardUserRequest;
import com.fastcampus.toyboard.user.service.BoardUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BoardUserController {
  private final BoardUserService boardUserService;

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @PostMapping("/login")
  public String login(
       BoardUserRequest.LoginDto loginDto) {
    System.out.println(
        "Username : " + loginDto.getUsername() + ", Password : " + loginDto.getPassword());
    System.out.println("Login Success.");

    return "redirect:/";
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


  @GetMapping("/sign-up/{username}/exists")
  public ResponseEntity<Boolean> checkUsernameDuplicate(@PathVariable String username) {
    return ResponseEntity.ok(boardUserService.usernameOverlap2(username));
  }
}
