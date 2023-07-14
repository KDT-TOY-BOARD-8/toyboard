package com.fastcampus.toyboard.board.controller;

import com.fastcampus.toyboard.board.dto.BoardDto;
import com.fastcampus.toyboard.board.model.Board;
import com.fastcampus.toyboard.board.model.BoardType;
import com.fastcampus.toyboard.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 게시글 작성 페이지 요청 처리
    @GetMapping("/write")
    public String showWriteForm(Model model) {
        model.addAttribute("boardDto", new BoardDto());
        return "board/write-form";
    }

    // 게시글 작성 요청 처리
    @PostMapping("/write")
    public String writeBoard(@ModelAttribute("boardDto") @Valid BoardDto boardDto,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "board/write-form";
        }

        // 게시글 작성 서비스 메서드 호출
        boardService.writeBoard(boardDto);

        return "redirect:/board/list";
    }

    // 새싹회원 게시판 목록 조회 요청 처리
    @GetMapping("/sprout")
    public String showSproutBoardList(Model model) {
        List<Board> sproutBoards = boardService.getBoardsByCategory(BoardType.SPROUT);
        model.addAttribute("boards", sproutBoards);
        return "board/board-list";
    }

    // 우수회원 게시판 목록 조회 요청 처리
    @GetMapping("/great")
    public String showGreatBoardList(Model model) {
        List<Board> greatBoards = boardService.getBoardsByCategory(BoardType.GREAT);
        model.addAttribute("boards", greatBoards);
        return "board/board-list";
    }

    // 게시글 목록 보기 조회 요쳥 처리
    @GetMapping("/list")
    public String showBoardList(Model model) {
        List<BoardDto> boards = boardService.getAllBoardsDesc();
        model.addAttribute("boards", boards);
        return "board/board-list";
    }

}
