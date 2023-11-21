package com.codereview.codereview.global.repository.board.querydsl;

import com.codereview.codereview.global.model.entity.Board;
import com.codereview.codereview.global.model.entity.QBoard;
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

public class BoardRepositoryCustomImpl extends QuerydslRepositorySupport implements BoardRepositoryCustom {

    private QBoard qBoard = QBoard.board;
    private QUser qUser = QUser.user;
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public BoardRepositoryCustomImpl(EntityManager em) {
        super(Board.class);
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ReviewResponse> findAllBoardPage(Pageable pageable, long count) {

        List<Board> boards = queryFactory
                .selectFrom(qBoard)
                .orderBy(qBoard.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<ReviewResponse> reviewResponses = boards.stream()
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

        Board board = queryFactory
                .selectFrom(qBoard)
                .leftJoin(qUser).on(qBoard.user.id.eq(qUser.id))
                .orderBy(qBoard.createdAt.desc())
                .where(boardIdEq(id))
                .fetchOne();

        ReviewOneResponse response = new ReviewOneResponse(
                board.getId(),
                board.getTitle(),
                board.getProblem(),
                board.getQuestion(),
                board.getUser().getNickname(),
                board.getCategory(),
                board.getStatus(),
                board.getViews(), //TODO: 수정 요망
                board.getCode(),
                board.getCreatedAt()
        );

        return Optional.of(response);
    }

    private BooleanExpression boardIdEq(Long id) {
        return isEmpty(id) ? null : qBoard.id.eq(id);
    }

}
