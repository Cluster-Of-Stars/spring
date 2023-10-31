package com.codereview.codereview.global.repository;

import com.codereview.codereview.global.model.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}