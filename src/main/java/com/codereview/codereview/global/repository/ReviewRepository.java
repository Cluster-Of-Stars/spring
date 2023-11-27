package com.codereview.codereview.global.repository;

import com.codereview.codereview.global.model.entity.Review;
import com.codereview.codereview.global.repository.review.querydsl.ReviewRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ReviewRepository extends
        JpaRepository<Review, Long>,
        ReviewRepositoryCustom,
        QuerydslPredicateExecutor {



}