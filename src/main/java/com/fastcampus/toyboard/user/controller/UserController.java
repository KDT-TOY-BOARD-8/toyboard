package com.fastcampus.toyboard.user.controller;

import com.fastcampus.toyboard.user.dto.UserRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @ResponseBody
  @PostMapping("/login")
  public UserRequest.LoginDto login(UserRequest.LoginDto loginDto) {
    System.out.println(loginDto);
    return loginDto;
  }

  @GetMapping("/sign-up")
  public String signUp() {
    return "sign-up";
  }

  @ResponseBody
  @PostMapping("/sign-up")
  public UserRequest.SignUpDto login(UserRequest.SignUpDto signUpDto) {
    System.out.println(signUpDto);
    return signUpDto;
  }
}
