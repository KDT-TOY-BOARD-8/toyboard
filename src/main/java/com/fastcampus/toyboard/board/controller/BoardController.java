package com.fastcampus.toyboard.board.controller;

import com.fastcampus.toyboard.board.dto.BoardDto;
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
}
