package com.codereview.codereview.global.repository.reviewview.querydsl;

import com.codereview.codereview.global.model.entity.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Optional;

import static org.springframework.util.StringUtils.isEmpty;

public class ReviewViewRepositoryCustomImpl extends QuerydslRepositorySupport implements ReviewViewRepositoryCustom {

    private QReviewView qReviewView = QReviewView.reviewView;
    private QReview qReview = QReview.review;
    private QUser qUser = QUser.user;
    private final JPAQueryFactory queryFactory;

    public ReviewViewRepositoryCustomImpl(EntityManager em) {
        super(ReviewHeart.class);
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<ReviewView> findReviewView(Long reviewId, Long userId) {
        ReviewView reviewView = queryFactory
                .selectFrom(qReviewView)
                .where(reviewIdEq(reviewId), userIdEq(userId))
                .fetchOne();

        return Optional.ofNullable(reviewView);
    }

    private BooleanExpression reviewIdEq(Long id) {
        return isEmpty(id) ? null : qReview.id.eq(id);
    }

    private BooleanExpression userIdEq(Long id) {
        return isEmpty(id) ? null : qUser.id.eq(id);
    }

}
