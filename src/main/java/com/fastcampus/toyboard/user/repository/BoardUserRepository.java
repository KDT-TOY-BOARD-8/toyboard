package com.fastcampus.toyboard.user.repository;

import com.fastcampus.toyboard.user.model.BoardUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardUserRepository extends JpaRepository<BoardUser, Long> {
  Optional<BoardUser> findBoardUserByNickname(String nickname);

  Optional<BoardUser> findBoardUserByUsername(String username);

  boolean existsByUsername(String username);
  boolean existsByEmail(String email);

  boolean existsByNickname(String nickname);

}
