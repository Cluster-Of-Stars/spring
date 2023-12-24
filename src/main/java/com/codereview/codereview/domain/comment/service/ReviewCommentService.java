package com.codereview.codereview.domain.comment.service;

import com.codereview.codereview.domain.comment.model.request.ReviewCommentRequest;
import com.codereview.codereview.domain.comment.model.response.ReviewCommentResponse;
import com.codereview.codereview.domain.util.ServiceUtil;
import com.codereview.codereview.global.model.entity.Review;
import com.codereview.codereview.global.model.entity.ReviewComment;
import com.codereview.codereview.global.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewCommentService {

    private final ServiceUtil serviceUtil;

    @Transactional
    public ResponseEntity createComment(ReviewCommentRequest commentRequest, Long reviewId, Long userId) {

        Review review = serviceUtil.getReview(reviewId);
        User user = serviceUtil.getUser(userId);

        serviceUtil.getCommentRepository().save(ReviewComment.builder()
                .content(commentRequest.content())
                .user(user)
                .review(review)
                .build());

        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity updateComment(ReviewCommentRequest commentRequest, Long commentId, Long userId) {
        ReviewComment reviewComment = serviceUtil.getReviewComment(commentId);

        serviceUtil.checkUser(reviewComment.getUser(), userId);

        reviewComment.updateContent(commentRequest.content());
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity deleteComment(Long commentId, Long userId) {
        ReviewComment reviewComment = serviceUtil.getReviewComment(commentId);

        serviceUtil.checkUser(reviewComment.getUser(), userId);

        serviceUtil.getCommentRepository().delete(reviewComment);
        return ResponseEntity.ok().build();
    }

    @Transactional(readOnly = true)
    public ResponseEntity selectComment(Long reviewId, Long userId) {

        List<ReviewComment> reviewComments = serviceUtil.getCommentRepository().findByReviewId(reviewId);

        List<ReviewCommentResponse> reviewCommentResponses = reviewComments.stream()
                .map(comment -> new ReviewCommentResponse(
                        comment.getContent(),
                        comment.getReviewCommentHearts().stream().count(),
                        !(serviceUtil.getCommentHeartRepository().findByUserIdAndReviewCommentId(userId, comment.getId()).isEmpty())
                ))
                .toList();

        return ResponseEntity.ok().body(reviewCommentResponses);
    }

}
