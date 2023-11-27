package com.codereview.codereview.review.service;

import com.codereview.codereview.global.error.errortype.BoardErrorType;
import com.codereview.codereview.global.error.exception.BoardExceptionImpl;
import com.codereview.codereview.global.model.entity.Review;
import com.codereview.codereview.global.model.entity.ReviewHeart;
import com.codereview.codereview.global.model.entity.ReviewView;
import com.codereview.codereview.global.model.entity.User;
import com.codereview.codereview.global.repository.ReviewRepository;
import com.codereview.codereview.global.repository.ReviewViewRepository;
import com.codereview.codereview.global.repository.UserRepository;
import com.codereview.codereview.review.model.request.ReviewCreateRequest;
import com.codereview.codereview.review.model.request.ReviewUpdateRequest;
import com.codereview.codereview.review.model.response.ReviewOneResponse;
import com.codereview.codereview.review.model.response.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository boardRepository;
    private final UserRepository userRepository;
    private final ReviewViewRepository reviewViewRepository;

    @Transactional
    public ResponseEntity createCodeReview(ReviewCreateRequest request, Long userId) {
        System.out.println(request);
        boardRepository.save(request.toBoard(request, getUser(userId)));
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity updateCodeReview(Long boardId, ReviewUpdateRequest request, Long userId) {
        Review review = getReview(boardId);

        checkUser(review.getUser(), getUser(userId));

        review.updateCodeReview(
                request.title(),
                request.code(),
                request.question(),
                request.problem()
        );

        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity deleteCodeReview(Long boardId, Long userId) {
        Review review = getReview(boardId);

        checkUser(review.getUser(), getUser(userId));

        boardRepository.delete(review);
        return ResponseEntity.ok().build();
    }

    @Transactional
    private Review getReview(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> {
                    throw new BoardExceptionImpl(BoardErrorType.BOARD_NOT_FOUND);
                });
    }

    @Transactional
    private User getUser(Long id) {
        //TODO: 예외처리 해야함.
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    throw new BoardExceptionImpl(BoardErrorType.BOARD_NOT_FOUND);
                });
    }

    private void checkUser(User codereviewUser, User user) {
        if (codereviewUser.getId() != user.getId()) {
            //TODO: 예외처리
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity selectCodeReview(Integer size, Integer page) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "createdAt"));

        Page<ReviewResponse> reviewResponses = boardRepository.findAllBoardPage(pageable, boardRepository.count());

        return ResponseEntity.ok()
                .body(reviewResponses);
    }

    @Transactional
    public ResponseEntity selectOneCodeReview(Long reviewId, Long userId) {
        ReviewOneResponse reviewResponse = boardRepository.findOneBoard(reviewId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException();
                }); //TODO: 예외처리

        validationView(reviewId, userId);

        return ResponseEntity.ok()
                .body(reviewResponse);
    }

    @Transactional
    private void validationView(Long reviewId, Long userId) {
        Review review = getReview(reviewId);
        User user = getUser(userId);

        Optional<ReviewView> reviewView = reviewViewRepository.findReviewView(reviewId, userId);

        if (reviewView.isEmpty()) {
            reviewViewRepository.save(ReviewView.builder()
                    .review(review)
                    .user(user)
                    .build()
            );
        }
    }

}
