package com.codereview.codereview.global.repository.board.querydsl;

import com.codereview.codereview.review.model.response.ReviewOneResponse;
import com.codereview.codereview.review.model.response.ReviewResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BoardRepositoryCustom {

    Page<ReviewResponse> findAllBoardPage(Pageable pageable, long count);

    Optional<ReviewOneResponse> findOneBoard(Long id);

}
