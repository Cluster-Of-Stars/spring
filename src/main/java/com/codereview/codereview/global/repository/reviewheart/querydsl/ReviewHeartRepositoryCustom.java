package com.codereview.codereview.global.repository.reviewheart.querydsl;

import com.codereview.codereview.global.model.entity.ReviewHeart;

import java.util.Optional;

public interface ReviewHeartRepositoryCustom {

    Optional<ReviewHeart> findReviewHeart(Long reviewId, Long userId);

}
