package com.fastcampus.toyboard.common;

import com.fastcampus.toyboard.user.model.BoardUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
  @GetMapping("/")
  public String root(@AuthenticationPrincipal BoardUser boardUser) {
    if (boardUser == null) {
      return "forward:/login";
    }

    return "forward:/board";
  }

  @GetMapping("/login-failed")
  public String loginFailed() {
    return "login-failed";
  }
}
