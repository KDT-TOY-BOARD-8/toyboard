package com.fastcampus.toyboard.board.repository;

import com.fastcampus.toyboard.board.model.Board;
import com.fastcampus.toyboard.board.model.BoardType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByBoardType(BoardType boardType);
    Page<Board> findByTitleContainingOrContentContainingOrNickNameContaining(String title, String content, String nickName, Pageable pageable);

}
