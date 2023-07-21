package com.fastcampus.toyboard.report.model;

import com.fastcampus.toyboard.board.model.Board;
import com.fastcampus.toyboard.user.model.BoardUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Report {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "board_id", nullable = false)
  private Board board; // 신고된 게시글

  @ManyToOne
  @JoinColumn(name = "user_id")
  private BoardUser boardUser; // 신고자

  @Enumerated(EnumType.STRING)
  private ReportType type; // 신고 유형

  @Column(nullable = false)
  private Boolean isProcessed = false; // 신고가 처리되었는지 여부
}
