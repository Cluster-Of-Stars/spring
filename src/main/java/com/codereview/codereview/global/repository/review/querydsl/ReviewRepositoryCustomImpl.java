package com.codereview.codereview.global.repository.review.querydsl;

import com.codereview.codereview.global.model.entity.QReview;
import com.codereview.codereview.global.model.entity.Review;
import com.codereview.codereview.global.model.entity.QUser;
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
import java.util.Optional;

import static org.springframework.util.StringUtils.isEmpty;

public class ReviewRepositoryCustomImpl extends QuerydslRepositorySupport implements ReviewRepositoryCustom {

    private QReview qReview = QReview.review;
    private QUser qUser = QUser.user;
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
                .orderBy(qReview.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<ReviewResponse> reviewResponses = reviews.stream()
                .map(board -> new ReviewResponse(
                        board.getId(),
                        board.getTitle(),
                        board.getProblem(),
                        board.getQuestion(),
                        board.getCategory(),
                        board.getStatus(),
                        0L, //TODO: 수정 요망
                        board.getCreatedAt()
                ))
                .toList();

        return new PageImpl<>(reviewResponses, pageable, 0);
    }

    @Override
    public Optional<ReviewOneResponse> findOneBoard(Long id) {

        Review review = queryFactory
                .selectFrom(qReview)
                .leftJoin(qUser).on(qReview.user.id.eq(qUser.id))
                .orderBy(qReview.createdAt.desc())
                .where(reviewIdEq(id))
                .fetchOne();

        ReviewOneResponse response = new ReviewOneResponse(
                review.getId(),
                review.getTitle(),
                review.getProblem(),
                review.getQuestion(),
                review.getUser().getNickname(),
                review.getCategory(),
                review.getStatus(),
                review.getViews(), //TODO: 수정 요망
                review.getCode(),
                review.getCreatedAt()
        );

        return Optional.of(response);
    }

    private BooleanExpression reviewIdEq(Long id) {
        return isEmpty(id) ? null : qReview.id.eq(id);
    }

}
