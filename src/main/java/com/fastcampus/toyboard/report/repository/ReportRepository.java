package com.fastcampus.toyboard.report.repository;

import com.fastcampus.toyboard.report.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByBoardIdAndReporter(Long boardId, String reporter);
}
