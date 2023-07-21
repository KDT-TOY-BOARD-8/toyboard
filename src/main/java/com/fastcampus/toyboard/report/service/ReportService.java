package com.fastcampus.toyboard.report.service;

import com.fastcampus.toyboard.report.model.ReportType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {

//  private final ReportRepository reportRepository;

  // 신고 생성
  public void createReport(Long boardId, String reporter, ReportType type) {
    //        // 동일 사용자에 의한 반복 신고 제한
    //        if (reportRepository.existsByBoardIdAndReporter(boardId, reporter)) {
    //            throw new IllegalArgumentException("이미 신고한 게시글입니다.");
    //        }
    //
    //        BoardDto boardDto = boardService.getBoardDto(boardId);
    //        Board board = new Board();
    //        board.setTitle(boardDto.getTitle());
    //        board.setContent(boardDto.getContent());
    ////        board.set(boardDto.getNickName());
    //        // TODO: boardDto의 CommentDto 객체들을 Comment 객체로 변환하여 board에 설정해야 함
    //
    //        Report report = new Report();
    //        report.setBoard(board);
    //        report.setReporter(reporter);
    //        report.setType(type);
    //        reportRepository.save(report);
  }

  // 신고 확인
  public void checkReport(Long reportId) {
    //        Report report = reportRepository.findById(reportId)
    //                .orElseThrow(() -> new IllegalArgumentException("해당 신고가 존재하지 않습니다. id=" +
    // reportId));
    //
    //        report.setIsProcessed(true);
    //        reportRepository.save(report);
  }
}
