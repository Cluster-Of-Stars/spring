package com.codereview.codereview.global.model.repository;

import com.codereview.codereview.global.model.entity.ReviewCommentHeart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewCommentHeartRepository extends JpaRepository<ReviewCommentHeart, Long> {

    Optional<ReviewCommentHeart> findByUserIdAndReviewCommentId(Long userId, Long reviewComment);
    Optional<ReviewCommentHeart> findByUserIdAndId(Long userId, Long commentId);

}