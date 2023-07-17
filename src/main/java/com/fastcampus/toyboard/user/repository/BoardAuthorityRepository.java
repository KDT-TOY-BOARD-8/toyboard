package com.fastcampus.toyboard.user.repository;

import com.fastcampus.toyboard.user.model.BoardAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardAuthorityRepository extends JpaRepository<BoardAuthority, Long> {}
