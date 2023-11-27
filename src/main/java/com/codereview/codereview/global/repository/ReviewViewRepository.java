package com.codereview.codereview.global.repository;

import com.codereview.codereview.global.model.entity.ReviewView;
import com.codereview.codereview.global.repository.reviewheart.querydsl.ReviewHeartRepositoryCustom;
import com.codereview.codereview.global.repository.reviewview.querydsl.ReviewViewRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ReviewViewRepository extends
        JpaRepository<ReviewView, Long>,
        ReviewViewRepositoryCustom,
        QuerydslPredicateExecutor {
}