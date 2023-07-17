package com.fastcampus.toyboard.board.controller;

import com.fastcampus.toyboard.board.dto.BoardDto;
import com.fastcampus.toyboard.board.model.Board;
import com.fastcampus.toyboard.board.model.BoardType;
import com.fastcampus.toyboard.board.service.BoardService;
import com.fastcampus.toyboard.report.model.ReportType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.security.Principal;

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
    public String showBoardList(Model model,
                                @RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "6") int size) {
        Pageable pageable = PageRequest.of(page -1 , size, Sort.by(Sort.Direction.DESC, "id"));
        Page<BoardDto> boards = boardService.getAllBoardsDesc(pageable);
        model.addAttribute("boards", boards);
        return "board/board-list";
    }

    // 게시글 검색 요청 처리
    @GetMapping("/search")
    public String searchBoards(@RequestParam String keyword, Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "6") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<BoardDto> boards = boardService.searchBoards(keyword, pageable);
        model.addAttribute("boards", boards);
        return "board/board-list";
    }

    // 게시글 상세보기 요청 처리
    @GetMapping("/{id}")
    public String viewBoard(@PathVariable Long id, Model model) {
        BoardDto board = boardService.getBoardDto(id);
        model.addAttribute("board", board);
        model.addAttribute("reportTypes", ReportType.values());
        return "board/board-detail";
    }

    // 게시글 삭제 요청 처리
    @DeleteMapping("/{id}")
    public String deleteBoard(@PathVariable Long id, Principal principal) {
        // 게시글 삭제 서비스 메서드 호출
        boardService.deleteBoard(id, principal.getName());

        return "redirect:/board/list";
    }
    // 게시글 수정 페이지 요청 처리
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        BoardDto board = boardService.getBoardDto(id);
        model.addAttribute("boardDto", board);
        return "board/edit-form";
    }

    // 게시글 수정 요청 처리
    @PostMapping("/{id}/edit")
    public String editBoard(@PathVariable Long id,
                            @ModelAttribute("boardDto") @Valid BoardDto boardDto,
                            BindingResult bindingResult,
                            Principal principal) {
        if (bindingResult.hasErrors()) {
            return "board/edit-form";
        }

        // 게시글 수정 서비스 메서드 호출
        boardService.updateBoard(id, boardDto, principal.getName());

        return "redirect:/board/" + id;
    }
}
