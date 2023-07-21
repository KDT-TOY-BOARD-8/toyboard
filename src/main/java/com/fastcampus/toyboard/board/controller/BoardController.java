package com.fastcampus.toyboard.board.controller;

import com.fastcampus.toyboard.board.dto.BoardRequestDto;
import com.fastcampus.toyboard.board.dto.BoardResponseDto;
import com.fastcampus.toyboard.board.dto.BoardResponseWithComment;
import com.fastcampus.toyboard.board.service.BoardService;
import com.fastcampus.toyboard.user.dto.BoardUserDto;
import com.fastcampus.toyboard.user.model.BoardUser;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
  private final BoardService boardService;

  @GetMapping
  public String boardList(
      @AuthenticationPrincipal BoardUser boardUser, Pageable pageable, ModelMap map) {
    String authority = boardUser.getAuthorities().stream().findFirst().orElseThrow().getAuthority();

    if (authority.equals("GREAT") || authority.equals("ADMIN")) {
      return "redirect:/board/great";
    } else if (authority.equals("SPROUT")) {
      return "redirect:/board/sprout";
    }

    map.addAttribute("boards", boardService.getBoardsByCategory("BLACK", pageable));
    map.addAttribute(
        "paginationBarNumbers",
        getPaginationBarNumbers(pageable.getPageNumber(), pageable.getPageNumber()));

    return "board/index";
  }

  @GetMapping("/sprout")
  public String boardSprout(
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

  @PreAuthorize("hasAuthority('GREAT')")
  @GetMapping("/great")
  public String boardGreat(
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

  public List<Integer> getPaginationBarNumbers(int currentPageNumber, int totalPages) {
    final int BAR_LENGTH = 5;

    int startNumber = Math.max(currentPageNumber - (BAR_LENGTH / 2), 0);
    int endNumber = Math.min(startNumber + BAR_LENGTH, totalPages);

    return IntStream.range(startNumber, endNumber).boxed().collect(Collectors.toList());
  }

  // 게시글 작성 페이지 요청 처리
  @GetMapping("/write")
  public String showWriteForm(@RequestParam String category,ModelMap map) {
    map.addAttribute("currentCategory", category);
    return "new";
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
  @GetMapping("/{boardId}")
  public String detail(@PathVariable Long boardId, ModelMap model) {
    BoardResponseWithComment boardResponseWithComment = boardService.getBoardByBoardId(boardId);
    model.addAttribute("board", boardResponseWithComment);
    model.addAttribute("comments", boardResponseWithComment.getCommentResponseWithChildren());
    System.out.println(
        "\n댓글 수 : "
            + boardResponseWithComment.getCommentResponseWithChildren().size()
            + "\n대댓글 수 : ");
    boardResponseWithComment
        .getCommentResponseWithChildren()
        .forEach(
            commentResponseWithChildren -> {
              System.out.println(commentResponseWithChildren.getChildComments().size());
              commentResponseWithChildren.getChildComments().forEach(System.out::println);
            });

    return "board/detail";
  }

  // 게시글 삭제 요청 처리
  @DeleteMapping("/{boardId}")
  public String deleteBoard(@PathVariable Long boardId) {
    String category = boardService.getBoardByBoardId(boardId).getCategory();
    // 게시글 삭제 서비스 메서드 호출
    boardService.deleteBoard(boardId);

    return "redirect:/board/" + category;
  }

  // 게시글 수정 페이지 요청 처리
  @GetMapping("/{boardId}/edit")
  public String showEditForm(@PathVariable Long boardId, Model model) {
    BoardResponseWithComment board = boardService.getBoardByBoardId(boardId);
    model.addAttribute("board", board);
    return "board/update-post";
  }

  // 게시글 수정 요청 처리
  @PutMapping("/{boardId}/edit")
  public String editBoard(
      @AuthenticationPrincipal BoardUser boardUser,
      @PathVariable Long boardId,
      BoardRequestDto boardRequestDto) {

    boardService.updateBoard(boardId, boardUser.getUserId(), boardRequestDto);

    return "redirect:/board/" + boardId;
  }
}
