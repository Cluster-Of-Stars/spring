package com.codereview.codereview.global.repository;

import com.codereview.codereview.global.model.entity.Board;
import com.codereview.codereview.global.repository.board.querydsl.BoardRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BoardRepository extends
        JpaRepository<Board, Long>,
        BoardRepositoryCustom,
        QuerydslPredicateExecutor {



}