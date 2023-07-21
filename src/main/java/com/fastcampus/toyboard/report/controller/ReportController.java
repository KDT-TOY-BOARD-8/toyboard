package com.fastcampus.toyboard.report.controller;

import com.fastcampus.toyboard.report.dto.ReportRequestDto;
import com.fastcampus.toyboard.report.model.ReportType;
import com.fastcampus.toyboard.report.service.ReportService;
import com.fastcampus.toyboard.user.model.BoardUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReportController {
  private final ReportService reportService;

  @GetMapping("/board/{boardId}/report")
  public String report(@PathVariable Long boardId, ModelMap map) {
    map.addAttribute("reportTypes", ReportType.values());
    map.addAttribute("boardId", boardId);
    return "board/report";
  }

  // 신고 생성 요청 처리
  @PostMapping("/board/{boardId}/report")
  public String reportBoard(
      @PathVariable Long boardId,
      @AuthenticationPrincipal BoardUser boardUser,
      ReportRequestDto reportRequestDto) {
    reportService.createReport(boardId, boardUser.getNickname(), reportRequestDto.getReason());
    return "redirect:/board/" + boardId;
  }
}
