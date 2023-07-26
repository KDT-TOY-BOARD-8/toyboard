package com.fastcampus.toyboard.user.repository;

import com.fastcampus.toyboard.user.model.BoardUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardUserRepository extends JpaRepository<BoardUser, Long> {

  Optional<BoardUser> findBoardUserByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  Boolean existsByNickname(String nickname);
}
