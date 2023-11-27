package com.codereview.codereview.global.repository.reviewheart.querydsl;

import com.codereview.codereview.global.model.entity.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Optional;

import static org.springframework.util.StringUtils.isEmpty;

public class ReviewHeartRepositoryCustomImpl extends QuerydslRepositorySupport implements ReviewHeartRepositoryCustom {


    private QReviewHeart qReviewHeart = QReviewHeart.reviewHeart;
    private QReview qReview = QReview.review;
    private QUser qUser = QUser.user;
    private final JPAQueryFactory queryFactory;

    public ReviewHeartRepositoryCustomImpl(EntityManager em) {
        super(ReviewView.class);
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<ReviewHeart> findReviewHeart(Long reviewId, Long userId) {
        ReviewHeart reviewHeart = queryFactory
                .selectFrom(qReviewHeart)
                .where(reviewIdEq(reviewId), userIdEq(userId))
                .fetchOne();

        return Optional.ofNullable(reviewHeart);
    }

    private BooleanExpression reviewIdEq(Long id) {
        return isEmpty(id) ? null : qReview.id.eq(id);
    }

    private BooleanExpression userIdEq(Long id) {
        return isEmpty(id) ? null : qUser.id.eq(id);
    }

}
