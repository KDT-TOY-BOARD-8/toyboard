package com.fastcampus.toyboard.board.repository;

import com.fastcampus.toyboard.board.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {

  @Query(
      value =
          "select b.boardId as boardId, bu.nickname as nickname, b.title as title, b.content as content, b.hide as hide,  b.createdAt as createdAt "
          + "from board_tb b "
          + "left join BoardUser bu on b.boardUser.userId = bu.userId "
          + "left join BoardAuthority ba on bu.userId=ba.userId "
          + "where ba.authority=:authority and (b.hide is null or b.hide = false) order by createdAt desc, boardId desc",
      countQuery =
          "select count(*) "
              + "from board_tb b "
              + "left join BoardUser bu on b.boardUser.userId = bu.userId "
              + "left join BoardAuthority ba on bu.userId=ba.userId "
              + "where ba.authority=:authority and (b.hide is null or b.hide = false)")
  Page<IBoard> findBoardsByAuthority(@Param("authority") String authority, Pageable pageable);

  @Query(
          value =
                  "select b.boardId as boardId, bu.nickname as nickname, b.title as title, b.content as content, b.hide as hide,  b.createdAt as createdAt "
                  + "from board_tb b "
                  + "left join BoardUser bu on b.boardUser.userId = bu.userId "
                  + "left join BoardAuthority ba on bu.userId=ba.userId "
                  + "where b.category=:category and (b.hide is null or b.hide = false) order by createdAt desc, boardId desc",
          countQuery =
                  "select count(*) "
                  + "from board_tb b "
                  + "left join BoardUser bu on b.boardUser.userId = bu.userId "
                  + "left join BoardAuthority ba on bu.userId=ba.userId "
                  + "where b.category=:category and (b.hide is null or b.hide = false)")
  Page<IBoard> findBoardsByCategory(@Param("category") String category, Pageable pageable);

  @Query(
      value =
          "select b.boardId as boardId, bu.nickname as nickname, b.title as title, b.content as content, b.hide as hide,  b.createdAt as createdAt "
          + "from board_tb b "
          + "left join BoardUser bu on b.boardUser.userId = bu.userId "
          + "where (b.hide is null or b.hide = false) order by createdAt desc, boardId desc ",
      countQuery =
          "select count(*) "
          + "from board_tb b "
          + "left join BoardUser bu on b.boardUser.userId = bu.userId "
          + "where (b.hide is null or b.hide = false)")
  Page<IBoard> findBoardsByHideNot(Pageable pageable);
}
