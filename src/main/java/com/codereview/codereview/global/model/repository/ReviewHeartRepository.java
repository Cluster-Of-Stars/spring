package com.codereview.codereview.global.model.repository;

import com.codereview.codereview.global.model.entity.ReviewHeart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewHeartRepository extends
        JpaRepository<ReviewHeart, Long>{

    Optional<ReviewHeart> findByReviewIdAndUserId(Long reviewId, Long userId);

}