package com.fastcampus.toyboard.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
  @RequestMapping("/")
  public String root() {
    return "forward:/board";
  }
}
