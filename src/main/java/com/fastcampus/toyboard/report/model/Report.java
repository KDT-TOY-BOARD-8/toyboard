package com.fastcampus.toyboard.report.model;

import com.fastcampus.toyboard.board.model.Board;
import com.fastcampus.toyboard.user.model.BoardUser;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "report_tb")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public class Report {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "board_id", nullable = false)
  private Board board; // 신고된 게시글

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private BoardUser boardUser; // 신고자

  @Enumerated(EnumType.STRING)
  private ReportType type; // 신고 유형

  private String detail;

  @Column(nullable = false)
  private Boolean isProcessed = false; // 신고가 처리되었는지 여부

  public static Report of(Board board, BoardUser boardUser, ReportType type, String detail) {
    return new Report(null, board, boardUser, type, detail, false);
  }
}
