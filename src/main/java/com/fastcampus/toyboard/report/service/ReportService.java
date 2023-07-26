package com.fastcampus.toyboard.report.service;

import com.fastcampus.toyboard.board.model.Board;
import com.fastcampus.toyboard.board.repository.BoardRepository;
import com.fastcampus.toyboard.report.dto.ReportRequestDto;
import com.fastcampus.toyboard.report.repository.ReportRepository;
import com.fastcampus.toyboard.user.model.BoardUser;
import com.fastcampus.toyboard.user.repository.BoardUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {

  private final ReportRepository reportRepository;
  private final BoardRepository boardRepository;
  private final BoardUserRepository boardUserRepository;

  // 신고 생성
  public void createReport(Long boardId, Long userId, ReportRequestDto reportRequestDto) {
    // 동일 사용자에 의한 반복 신고 제한
    if (reportRepository.existsByBoard_BoardIdAndBoardUser_UserId(boardId, userId)) {
      throw new IllegalArgumentException("이미 신고한 게시글입니다.");
    }

    Board board =
        boardRepository
            .findById(boardId)
            .orElseThrow(
                () -> {
                  throw new IllegalArgumentException("존재하지 않는 게시글입니다.");
                });
    BoardUser boardUser =
        boardUserRepository
            .findById(userId)
            .orElseThrow(
                () -> {
                  throw new UsernameNotFoundException("로그인 상태를 확인하세요");
                });

    reportRepository.save(reportRequestDto.toEntity(board, boardUser));
  }
}
