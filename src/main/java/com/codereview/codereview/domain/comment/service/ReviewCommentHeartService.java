package com.codereview.codereview.domain.comment.service;

import com.codereview.codereview.domain.util.ServiceUtil;
import com.codereview.codereview.global.model.entity.ReviewComment;
import com.codereview.codereview.global.model.entity.ReviewCommentHeart;
import com.codereview.codereview.global.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewCommentHeartService {

    private final ServiceUtil serviceUtil;

    public ResponseEntity reviewCommentHeart(Long commentId, Long reviewId, Long userId) {
        ReviewComment comment = serviceUtil.getReviewComment(commentId);
        User user = serviceUtil.getUser(userId);

        Optional<ReviewCommentHeart> commentHeart = serviceUtil.getCommentHeartRepository()
                .findByUserIdAndId(userId, commentId);

        if (commentHeart.isEmpty()) {
            serviceUtil.getCommentHeartRepository().save(
                    ReviewCommentHeart.builder()
                            .user(user)
                            .reviewComment(comment)
                            .build()
            );
            return ResponseEntity.ok().build();
        }
        serviceUtil.getCommentHeartRepository().delete(commentHeart.get());
        return ResponseEntity.ok().build();
    }

}
