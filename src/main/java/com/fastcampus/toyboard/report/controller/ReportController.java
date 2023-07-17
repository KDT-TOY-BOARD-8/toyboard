package com.fastcampus.toyboard.report.controller;

import com.fastcampus.toyboard.board.service.BoardService;
import com.fastcampus.toyboard.report.model.Report;
import com.fastcampus.toyboard.report.model.ReportType;
import com.fastcampus.toyboard.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final BoardService boardService;
    private final ReportService reportService;

    @Autowired
    public ReportController(BoardService boardService, ReportService reportService) {
        this.boardService = boardService;
        this.reportService = reportService;
    }

    // 신고 생성 요청 처리
    @PostMapping("/{boardId}")
    public String reportBoard(@PathVariable Long boardId,
                              @RequestParam ReportType type,
                              Principal principal) {
        reportService.createReport(boardId, principal.getName(), type);
        return "redirect:/board/" + boardId;
    }

    // 신고 확인 요청 처리
    @PostMapping("/{reportId}/check")
    public String checkReport(@PathVariable Long reportId) {
        reportService.checkReport(reportId);
        return "redirect:/report/list";
    }

    // 신고 목록 조회 요청 처리
    @GetMapping("/list")
    public List<Report> showReportList() {
        return reportService.getAllReports();
    }

    // 처리되지 않은 신고 목록 조회 요청 처리
    @GetMapping("/list/unprocessed")
    public List<Report> showUnprocessedReportList() {
        return reportService.getUnprocessedReports();
    }
}

