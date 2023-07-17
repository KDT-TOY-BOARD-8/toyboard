package com.fastcampus.toyboard.report.model;

import com.fastcampus.toyboard.board.model.Board;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Column(nullable = false)
    private String reporter; // 신고자

    @Enumerated(EnumType.STRING)
    private ReportType type; // 신고 유형

    @Column(nullable = false)
    private Boolean isProcessed = false; // 신고가 처리되었는지 여부

}

