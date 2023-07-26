package com.fastcampus.toyboard.report.controller;

import com.fastcampus.toyboard.report.dto.ReportRequestDto;
import com.fastcampus.toyboard.report.model.ReportType;
import com.fastcampus.toyboard.report.service.ReportService;
import com.fastcampus.toyboard.user.model.BoardUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ReportController {
  private final ReportService reportService;

  @ModelAttribute("reportTypes")
  public ReportType[] reportTypes() {
    return ReportType.values();
  }

  @GetMapping("/board/{category}/{boardId}/report")
  public String report(@PathVariable String category, @PathVariable Long boardId, ModelMap map) {
    map.addAttribute("reportTypes", ReportType.values());
    map.addAttribute("category", category);
    map.addAttribute("boardId", boardId);
    return "board/report-post";
  }

  // 신고 생성 요청 처리
  @PostMapping("/board/{category}/{boardId}/report")
  public String reportBoard(
      @PathVariable String category,
      @PathVariable Long boardId,
      @AuthenticationPrincipal BoardUser boardUser,
      @ModelAttribute("report") ReportRequestDto reportRequestDto) {
    log.info("ReportRequestDto : {}", reportRequestDto);
    reportService.createReport(boardId, boardUser.getUserId(), reportRequestDto);
    return "redirect:/board/" + category + "/" + boardId;
  }
}
