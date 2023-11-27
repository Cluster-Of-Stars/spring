package com.codereview.codereview.global.repository;

import com.codereview.codereview.global.model.entity.ReviewHeart;
import com.codereview.codereview.global.model.entity.User;
import com.codereview.codereview.global.repository.review.querydsl.ReviewRepositoryCustom;
import com.codereview.codereview.global.repository.reviewheart.querydsl.ReviewHeartRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface ReviewHeartRepository extends
        JpaRepository<ReviewHeart, Long>,
        ReviewHeartRepositoryCustom,
        QuerydslPredicateExecutor {

}