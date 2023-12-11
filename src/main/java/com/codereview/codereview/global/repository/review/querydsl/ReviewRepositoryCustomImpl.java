package com.codereview.codereview.global.repository.review.querydsl;

import com.codereview.codereview.global.model.entity.*;
import com.codereview.codereview.review.model.response.ReviewOneResponse;
import com.codereview.codereview.review.model.response.ReviewResponse;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.util.StringUtils.isEmpty;

public class ReviewRepositoryCustomImpl extends QuerydslRepositorySupport implements ReviewRepositoryCustom {

    private QReview qReview = QReview.review;
    private QUser qUser = QUser.user;
    private QReviewHeart qReviewHeart = QReviewHeart.reviewHeart;
    private QReviewView qReviewView = QReviewView.reviewView;
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public ReviewRepositoryCustomImpl(EntityManager em) {
        super(Review.class);
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ReviewResponse> findAllBoardPage(Pageable pageable, long count) {

        List<Review> reviews = queryFactory
                .selectFrom(qReview)
                .leftJoin(qUser).on(qReview.user.id.eq(qUser.id))
                .orderBy(qReview.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<ReviewResponse> reviewResponses = reviews.stream()
                .map(review -> new ReviewResponse(
                        review.getId(),
                        review.getUser().getNickname(),
                        review.getTitle(),
                        review.getProblem(),
                        review.getQuestion(),
                        review.getCategory(),
                        review.getStatus(),
                        (long) review.getReviewHearts().size(), //TODO: 수정 요망
                        (long) review.getReviewViews().size(),
                        review.getCreatedAt()
                ))
                .toList();

        return new PageImpl<>(reviewResponses, pageable, 0);
    }

    @Override
    public Page<ReviewResponse> findAllCateogryReviewPage(Pageable pageable, long count, String category) {

        List<Review> reviews = queryFactory
                .selectFrom(qReview)
                .where(reviewCategoryEq(category))
                .leftJoin(qUser).on(qReview.user.id.eq(qUser.id))
                .orderBy(qReview.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<ReviewResponse> reviewResponses = reviews.stream()
                .map(review -> new ReviewResponse(
                        review.getId(),
                        review.getUser().getNickname(),
                        review.getTitle(),
                        review.getProblem(),
                        review.getQuestion(),
                        review.getCategory(),
                        review.getStatus(),
                        (long) review.getReviewHearts().size(), //TODO: 수정 요망
                        (long) review.getReviewViews().size(),
                        review.getCreatedAt()
                ))
                .toList();

        return new PageImpl<>(reviewResponses, pageable, 0);
    }

    @Override
    public Optional<ReviewOneResponse> findOneBoard(Long id, Long userId) {

        Review review = queryFactory
                .selectFrom(qReview)
                .where(reviewIdEq(id))
                .fetchOne();

        if (Objects.isNull(review)) {
            return Optional.empty();
        }

        ReviewHeart reviewHeart = queryFactory
                .selectFrom(qReviewHeart)
                .where(reviewHeartReviewIdEq(id), reviewHeartUserIdEq(userId))
                .fetchOne();

        ReviewOneResponse response = new ReviewOneResponse(
                review.getId(),
                review.getTitle(),
                review.getProblem(),
                review.getQuestion(),
                review.getCategory(),
                review.getStatus(),
                (long) review.getReviewHearts().size(),
                (long) review.getReviewViews().size(),
                review.getCode(),
                heartCheck(reviewHeart),
                review.getCreatedAt()
        );

        return Optional.of(response);
    }

    private boolean heartCheck(ReviewHeart reviewHeart) {
        return reviewHeart != null;
    }

    private BooleanExpression reviewHeartUserIdEq(Long id) {
        return isEmpty(id) ? null : qReviewHeart.review.user.id.eq(id);
    }

    private BooleanExpression reviewHeartReviewIdEq(Long id) {
        return isEmpty(id) ? null : qReviewHeart.review.id.eq(id);
    }


    private BooleanExpression reviewIdEq(Long id) {
        return isEmpty(id) ? null : qReview.id.eq(id);
    }

    private BooleanExpression reviewCategoryEq(String category) {
        return isEmpty(category) ? null : qReview.category.any().in(category);
    }

}
