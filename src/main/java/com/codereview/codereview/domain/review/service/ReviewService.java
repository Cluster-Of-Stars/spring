package com.codereview.codereview.domain.review.service;

import com.codereview.codereview.domain.review.model.request.ReviewCreateRequest;
import com.codereview.codereview.domain.review.model.request.ReviewUpdateRequest;
import com.codereview.codereview.domain.review.model.response.ReviewOneResponse;
import com.codereview.codereview.domain.review.model.response.ReviewResponse;
import com.codereview.codereview.domain.util.ServiceUtil;
import com.codereview.codereview.global.model.entity.Category;
import com.codereview.codereview.global.model.entity.Review;
import com.codereview.codereview.global.model.entity.ReviewHeart;
import com.codereview.codereview.global.model.entity.type.CodeReviewStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ServiceUtil serviceUtil;

    @Transactional
    public ResponseEntity createCodeReview(ReviewCreateRequest request, Long userId) {

        serviceUtil.getReviewRepository().save(request.toReview(request, serviceUtil.getUser(userId),
                serviceUtil.getCategory(request.category())));

        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity updateCodeReview(Long boardId, ReviewUpdateRequest request, Long userId) {
        Review review = serviceUtil.getReview(boardId);

        serviceUtil.checkUser(review.getUser(), userId);

        review.updateCodeReview(
                request.title(),
                request.code(),
                request.question(),
                request.problem()
        );

        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity deleteCodeReview(Long boardId, Long userId) {
        Review review = serviceUtil.getReview(boardId);

        serviceUtil.checkUser(review.getUser(), userId);

        review.deleteCodeReviewCategory();
        serviceUtil.getReviewRepository().delete(review);
        return ResponseEntity.ok().build();
    }

    @Transactional(readOnly = true)
    public ResponseEntity selectCodeReview(Integer size, Integer page) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "createdAt"));

        Page<Review> reviews = serviceUtil.getReviewRepository().findAll(pageable);

        List<ReviewResponse> reviewResponses = reviews.stream()
                .map(review -> new ReviewResponse(
                                review.getId(),
                                review.getUser().getNickname(),
                                review.getTitle(),
                                review.getProblem(),
                                review.getQuestion(),
                                serviceUtil.getStringCategory(review.getCategory()),
                                review.getStatus(),
                                review.getReviewHearts().stream().count(),
                                review.getReviewViews().stream().count(),
                                review.getCreatedAt()
                        )
                ).toList();

        return ResponseEntity.ok()
                .body(new PageImpl<>(reviewResponses, pageable, reviews.getTotalPages()));
    }

    @Transactional
    public ResponseEntity selectOneCodeReview(Long reviewId, Long userId) {

        Review review = serviceUtil.getReview(reviewId);

        Optional<ReviewHeart> reviewHeart = serviceUtil.getReviewHeartRepository().findByReviewIdAndUserId(reviewId, userId);

        ReviewOneResponse reviewResponse = new ReviewOneResponse(
                review.getId(),
                review.getTitle(),
                review.getProblem(),
                review.getQuestion(),
                serviceUtil.getStringCategory(review.getCategory()),
                review.getStatus(),
                review.getReviewHearts().stream().count(),
                review.getReviewViews().stream().count(),
                review.getCode(),
                !(reviewHeart.isEmpty()),
                review.getCreatedAt()
        );

        return ResponseEntity.ok()
                .body(reviewResponse);
    }

    @Transactional
    public ResponseEntity successCodeReview(Long boardId, Long userId) {

        Review review = serviceUtil.getReview(boardId);
        serviceUtil.checkUser(review.getUser(), userId);

        review.updateCodeReview(CodeReviewStatus.CODE_CLEAR);

        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity selectCategoryCodeReview(Integer size, int page, String category) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "createdAt"));

        Category categoryEntity = serviceUtil.getCategory(Collections.singletonList(category)).get(0);

        Page<Review> reviews = serviceUtil.getReviewRepository().findByCategory(categoryEntity, pageable);

        List<ReviewResponse> reviewResponses = reviews.stream()
                .map(review -> new ReviewResponse(
                        review.getId(),
                        review.getUser().getNickname(),
                        review.getTitle(),
                        review.getProblem(),
                        review.getQuestion(),
                        serviceUtil.getStringCategory(review.getCategory()),
                        review.getStatus(),
                        review.getReviewHearts().stream().count(),
                        review.getReviewViews().stream().count(),
                        review.getCreatedAt()
                )).toList();

        return ResponseEntity.ok()
                .body(new PageImpl<>(reviewResponses, pageable, reviews.getTotalPages()));
    }


}
