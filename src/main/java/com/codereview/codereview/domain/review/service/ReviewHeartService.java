package com.codereview.codereview.domain.review.service;

import com.codereview.codereview.domain.util.ServiceUtil;
import com.codereview.codereview.global.model.entity.Review;
import com.codereview.codereview.global.model.entity.ReviewHeart;
import com.codereview.codereview.global.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewHeartService {

    private final ServiceUtil serviceUtil;

    @Transactional
    public ResponseEntity codeReviewHeart(Long reviewId, Long userId) {
        Review review = serviceUtil.getReview(reviewId);
        User user = serviceUtil.getUser(userId);

        Optional<ReviewHeart> reviewHeart = serviceUtil.getReviewHeartRepository().findByReviewIdAndUserId(reviewId, userId);

        if (reviewHeart.isEmpty()) {
            serviceUtil.getReviewHeartRepository().save(ReviewHeart.builder()
                    .review(review)
                    .user(user)
                    .build()
            );
            return ResponseEntity.ok().build();
        }

        serviceUtil.getReviewHeartRepository().delete(reviewHeart.get());
        return ResponseEntity.ok().build();
    }

}
