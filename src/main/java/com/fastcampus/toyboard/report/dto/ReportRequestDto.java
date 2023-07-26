package com.fastcampus.toyboard.report.dto;

import com.fastcampus.toyboard.board.model.Board;
import com.fastcampus.toyboard.report.model.Report;
import com.fastcampus.toyboard.report.model.ReportType;
import com.fastcampus.toyboard.user.model.BoardUser;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReportRequestDto {
  private ReportType reportType;
  private String detail;

  public Report toEntity(Board board, BoardUser boardUser) {
    return Report.of(board, boardUser, reportType, detail);
  }
}
