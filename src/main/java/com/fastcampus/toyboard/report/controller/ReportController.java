package com.fastcampus.toyboard.report.controller;

import com.fastcampus.toyboard.board.service.BoardService;
import com.fastcampus.toyboard.report.model.Report;
import com.fastcampus.toyboard.report.model.ReportType;
import com.fastcampus.toyboard.report.service.ReportService;
import com.fastcampus.toyboard.user.model.BoardUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class ReportController {
  private final ReportService reportService;

  @GetMapping("/board/{boardId}/report")
  public String report(@PathVariable Long boardId, ModelMap map) {
    return "board/report";
  }

  // 신고 생성 요청 처리
  @PostMapping("/board/{boardId}/report")
  public String reportBoard(
      @PathVariable Long boardId,
      @RequestParam ReportType type,
      @AuthenticationPrincipal BoardUser boardUser) {
    reportService.createReport(boardId, boardUser.getNickname(), type);
    return "redirect:/board/" + boardId;
  }
}
