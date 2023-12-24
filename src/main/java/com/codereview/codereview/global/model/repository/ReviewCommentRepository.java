package com.codereview.codereview.global.model.repository;

import com.codereview.codereview.global.model.entity.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewCommentRepository extends
        JpaRepository<ReviewComment, Long>{

    List<ReviewComment> findByReviewId(Long review);

}