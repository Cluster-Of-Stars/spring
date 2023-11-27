package com.codereview.codereview.review.service;

import com.codereview.codereview.global.error.errortype.BoardErrorType;
import com.codereview.codereview.global.error.exception.BoardExceptionImpl;
import com.codereview.codereview.global.model.entity.Review;
import com.codereview.codereview.global.model.entity.ReviewHeart;
import com.codereview.codereview.global.model.entity.ReviewView;
import com.codereview.codereview.global.model.entity.User;
import com.codereview.codereview.global.repository.ReviewHeartRepository;
import com.codereview.codereview.global.repository.ReviewRepository;
import com.codereview.codereview.global.repository.ReviewViewRepository;
import com.codereview.codereview.global.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewHeartService {

    private final ReviewRepository boardRepository;
    private final UserRepository userRepository;
    private final ReviewHeartRepository reviewHeartRepository;

    @Transactional
    public ResponseEntity codeReviewHeart(Long reviewId, Long userId) {
        Review review = getReview(reviewId);
        User user = getUser(userId);

        Optional<ReviewHeart> reviewHeart = reviewHeartRepository.findReviewHeart(reviewId, userId);

        if (reviewHeart.isEmpty()) {
            reviewHeartRepository.save(ReviewHeart.builder()
                    .review(review)
                    .user(user)
                    .build()
            );
            return ResponseEntity.ok().build();
        }

        reviewHeartRepository.deleteById(reviewHeart.get().getId());
        return ResponseEntity.ok().build();
    }

    @Transactional
    private Review getReview(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> {
                    throw new BoardExceptionImpl(BoardErrorType.BOARD_NOT_FOUND);
                });
    }

    @Transactional
    private User getUser(Long id) {
        //TODO: 예외처리 해야함.
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    throw new BoardExceptionImpl(BoardErrorType.BOARD_NOT_FOUND);
                });
    }

}
