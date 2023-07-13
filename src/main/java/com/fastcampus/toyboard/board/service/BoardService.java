package com.fastcampus.toyboard.board.service;

import com.fastcampus.toyboard.board.model.BoardType;
import com.fastcampus.toyboard.board.dto.BoardDto;
import com.fastcampus.toyboard.board.model.Board;
import com.fastcampus.toyboard.board.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void writeBoard(BoardDto boardDto) {
        Board board = new Board();
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());

        // 게시판에 따라 게시글을 작성
        if (boardDto.getCategory().equalsIgnoreCase("새싹회원")) {
            // 새싹회원 게시판에 작성
            board.setBoardType(BoardType.SPROUT);
        } else if (boardDto.getCategory().equalsIgnoreCase("우수회원")) {
            // 우수회원 게시판에 작성
            board.setBoardType(BoardType.GREAT);
        }

        boardRepository.save(board);
    }
    public List<Board> getBoardsByCategory(BoardType boardType) {
        return boardRepository.findByBoardType(boardType);
    }
}
