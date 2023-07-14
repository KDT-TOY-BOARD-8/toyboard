package com.fastcampus.toyboard.board.service;

import com.fastcampus.toyboard.board.model.BoardType;
import com.fastcampus.toyboard.board.dto.BoardDto;
import com.fastcampus.toyboard.board.model.Board;
import com.fastcampus.toyboard.board.repository.BoardRepository;
import org.springframework.data.domain.Sort;
import java.util.ArrayList;
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

    public List<BoardDto> getAllBoardsDesc() {
        // id 기준으로 내림차순 정렬하여 모든 게시글 가져오기
        List<Board> boards = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        // Board 객체 리스트를 BoardDto 객체 리스트로 변환
        List<BoardDto> boardDtos = new ArrayList<>();
        for (Board board : boards) {
            BoardDto boardDto = new BoardDto();
            boardDto.setTitle(board.getTitle());
            boardDto.setContent(board.getContent());
            // TODO: user의 nickName, thumbnail 정보를 가져오고 이를 BoardDto에 설정해야 함
            boardDtos.add(boardDto);
        }

        return boardDtos;
    }

}
