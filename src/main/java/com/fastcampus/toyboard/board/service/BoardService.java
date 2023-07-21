package com.fastcampus.toyboard.board.service;

import com.fastcampus.toyboard.board.dto.BoardResponseWithComment;
import com.fastcampus.toyboard.board.dto.BoardDto;
import com.fastcampus.toyboard.board.dto.BoardRequestDto;
import com.fastcampus.toyboard.board.dto.BoardResponseDto;
import com.fastcampus.toyboard.board.model.Board;
import com.fastcampus.toyboard.board.repository.BoardRepository;

import com.fastcampus.toyboard.comment.model.ChildComment;
import com.fastcampus.toyboard.comment.model.Comment;
import com.fastcampus.toyboard.comment.repository.ChildCommentRepository;
import com.fastcampus.toyboard.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
  private final BoardRepository boardRepository;
  private final CommentRepository commentRepository;
  private final ChildCommentRepository childCommentRepository;

  public void saveBoard(BoardDto boardDto) {
    boardRepository.save(BoardDto.toEntity(boardDto));
  }

  public Page<BoardResponseDto> getAllBoardsDesc(Pageable pageable) {
    // 최초 작성일 기준 내림차순
    return boardRepository.findAll(pageable).map(BoardResponseDto::of);
  }

  public Page<BoardResponseDto> getBoardsByCategory(String category, Pageable pageable) {
    return boardRepository
        .findBoardsByCategory(category, pageable)
        .map(
            resp ->
                new BoardResponseDto(
                    resp.getBoardId(),
                    resp.getNickname(),
                    resp.getTitle(),
                    resp.getContent(),
                    resp.getCategory(),
                    resp.getCreatedAt()));
  }

  public Page<BoardResponseDto> getBoards(Pageable pageable) {
    return boardRepository
        .findBoardsByHideNot(pageable)
        .map(
            resp ->
                new BoardResponseDto(
                    resp.getBoardId(),
                    resp.getNickname(),
                    resp.getTitle(),
                    resp.getContent(),
                    resp.getCategory(),
                    resp.getCreatedAt()));
  }

  /**
   * 게시글 상세보기
   *
   * @param boardId 게시글 ID
   * @return BoardResponseDto
   */
  public BoardResponseWithComment getBoardByBoardId(Long boardId) {
    return BoardResponseWithComment.of(
        boardRepository
            .findById(boardId)
            .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + boardId)));
  }

  public void deleteBoard(Long boardId) {
    List<Comment> commentList = commentRepository.findCommentsByBoard_BoardId(boardId);
    for (Comment comment : commentList) {
      if (comment.getChildComments() != null) {
        childCommentRepository
            .findChildCommentsByParentComment_CommentId(comment.getCommentId())
            .stream()
            .map(ChildComment::getChildCommentId)
            .forEach(childCommentRepository::deleteById);
      }
      commentRepository.deleteById(comment.getCommentId());
    }

    // 게시글 삭제
    boardRepository.deleteById(boardId);
  }

  public void updateBoard(Long boardId, Long boardUserId, BoardRequestDto boardRequestDto) {
    Board board =
        boardRepository
            .findById(boardId)
            .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + boardId));

    if (!board.getBoardUser().getUserId().equals(boardUserId)) {
      throw new IllegalArgumentException("해당 게시글을 수정할 권한이 없습니다.");
    }

    // 게시글 수정
    board.setTitle(boardRequestDto.getTitle());
    board.setContent(boardRequestDto.getContent());

    boardRepository.save(board);
  }
}
