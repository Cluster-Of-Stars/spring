package com.codereview.codereview.global.repository.reviewview.querydsl;

import com.codereview.codereview.global.model.entity.ReviewHeart;
import com.codereview.codereview.global.model.entity.ReviewView;

import java.util.Optional;

public interface ReviewViewRepositoryCustom {

    Optional<ReviewView> findReviewView(Long reviewId, Long userId);

}
