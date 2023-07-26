package com.fastcampus.toyboard.board.controller;

import com.fastcampus.toyboard.board.dto.BoardRequestDto;
import com.fastcampus.toyboard.board.dto.BoardResponseDto;
import com.fastcampus.toyboard.board.dto.BoardResponseWithComment;
import com.fastcampus.toyboard.board.repository.BoardRepository;
import com.fastcampus.toyboard.board.service.BoardService;
import com.fastcampus.toyboard.user.dto.BoardUserDto;
import com.fastcampus.toyboard.user.model.BoardUser;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
  private final BoardService boardService;
  private final BoardRepository boardRepository;

  @GetMapping
  public String boardList(
      @AuthenticationPrincipal BoardUser boardUser, Pageable pageable, ModelMap map) {
    String authority = boardUser.getAuthorities().stream().findFirst().orElseThrow().getAuthority();

    if (authority.equals("GREAT")) {
      return "redirect:/board/great";
    }

    if (authority.equals("SPROUT")) {
      return "redirect:/board/sprout";
    }

    map.addAttribute("boards", boardService.getBoardsByCategory("BLACK", pageable));
    map.addAttribute(
        "paginationBarNumbers",
        getPaginationBarNumbers(pageable.getPageNumber(), pageable.getPageNumber()));

    return "board/index";
  }

  @GetMapping("/sprout")
  public String boardsSprout(
      @SortDefault.SortDefaults({
            @SortDefault(
                sort = {"createdAt"},
                direction = Sort.Direction.DESC),
            @SortDefault(
                sort = {"boardId"},
                direction = Sort.Direction.DESC)
          })
          @PageableDefault(size = 6)
          Pageable pageable,
      ModelMap map) {
    Page<BoardResponseDto> boards = boardService.getBoardsByCategory("sprout", pageable);

    List<Integer> barNumbers =
        getPaginationBarNumbers(pageable.getPageNumber(), boards.getTotalPages());

    map.addAttribute("currentCategory", "sprout");
    map.addAttribute("boards", boards);
    map.addAttribute("paginationBarNumbers", barNumbers);

    return "board/index";
  }

  @GetMapping("/great")
  public String boardsGreat(
      @SortDefault.SortDefaults({
            @SortDefault(
                sort = {"createdAt"},
                direction = Sort.Direction.DESC),
            @SortDefault(
                sort = {"boardId"},
                direction = Sort.Direction.DESC)
          })
          @PageableDefault(size = 6)
          Pageable pageable,
      ModelMap map) {
    Page<BoardResponseDto> boards = boardService.getBoardsByCategory("great", pageable);

    List<Integer> barNumbers =
        getPaginationBarNumbers(pageable.getPageNumber(), boards.getTotalPages());

    map.addAttribute("currentCategory", "great");
    map.addAttribute("boards", boards);
    map.addAttribute("paginationBarNumbers", barNumbers);

    return "board/index";
  }

  private List<Integer> getPaginationBarNumbers(int currentPageNumber, int totalPages) {
    final int BAR_LENGTH = 5;

    int startNumber = Math.max(currentPageNumber - (BAR_LENGTH / 2), 0);
    int endNumber = Math.min(startNumber + BAR_LENGTH, totalPages);

    return IntStream.range(startNumber, endNumber).boxed().collect(Collectors.toList());
  }

  // 게시글 작성 페이지 요청 처리
  @GetMapping("/write")
  public String showWriteForm(@RequestParam String category, ModelMap map) {
    map.addAttribute("currentCategory", category);
    return "board/write-post";
  }

  // 게시글 작성 요청 처리
  @PostMapping("/write")
  public String writeBoard(
      @RequestParam String category,
      @AuthenticationPrincipal BoardUser boardUser,
      BoardRequestDto boardRequestDto) {

    // 게시글 작성 서비스 메서드 호출
    boardService.saveBoard(
        boardRequestDto.toBoardDto(BoardUserDto.fromEntity(boardUser), category));

    return "redirect:/board/" + category;
  }

  // 게시글 검색 요청 처리
  public String searchBoards(
      @RequestParam String keyword,
      Model model,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "6") int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
    //    Page<BoardDto> boards = boardService.searchBoards(keyword, pageable);
    //    model.addAttribute("boards", boards);
    return "index";
  }

  // 게시글 상세보기 요청 처리
  @GetMapping("/{category}/{boardId}")
  public String detail(
      @AuthenticationPrincipal BoardUser boardUser,
      @PathVariable String category,
      @PathVariable Long boardId,
      ModelMap model) {

    if (!boardUser.getAuthorities().stream()
            .findFirst()
            .orElseThrow()
            .getAuthority()
            .toLowerCase()
            .equals(category)
        || !boardRepository
            .findBoardByBoardId(boardId)
            .orElseThrow()
            .getCategory()
            .equals(category)) {
      throw new AccessDeniedException("접근 권한이 없습니다.");
    }

    BoardResponseWithComment boardResponseWithComment = boardService.getBoardByBoardId(boardId);
    model.addAttribute("board", boardResponseWithComment);
    model.addAttribute("category", category);

    return "board/detail";
  }

  // 게시글 삭제 요청 처리
  @DeleteMapping("/{category}/{boardId}")
  public String deleteBoard(@PathVariable String category, @PathVariable Long boardId) {
    // 게시글 삭제 서비스 메서드 호출
    boardService.deleteBoard(boardId);

    return "redirect:/board/" + category;
  }

  // 게시글 수정 페이지 요청 처리
  @GetMapping("/{category}/{boardId}/edit")
  public String showEditForm(
      @PathVariable String category, @PathVariable Long boardId, Model model) {
    BoardResponseWithComment board = boardService.getBoardByBoardId(boardId);
    model.addAttribute("board", board);
    model.addAttribute("category", category);
    return "board/update-post";
  }

  // 게시글 수정 요청 처리
  @PutMapping("/{category}/{boardId}/edit")
  public String editBoard(
      @AuthenticationPrincipal BoardUser boardUser,
      @PathVariable String category,
      @PathVariable Long boardId,
      BoardRequestDto boardRequestDto) {

    boardService.updateBoard(boardId, boardUser.getUserId(), boardRequestDto);

    return "redirect:/board/" + category + "/" + boardId;
  }
}
