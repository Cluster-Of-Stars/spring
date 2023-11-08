package com.codereview.codereview.review.service;

import com.codereview.codereview.global.error.errortype.BoardErrorType;
import com.codereview.codereview.global.error.exception.BoardExceptionImpl;
import com.codereview.codereview.global.model.entity.Board;
import com.codereview.codereview.global.model.entity.User;
import com.codereview.codereview.global.repository.BoardRepository;
import com.codereview.codereview.review.model.request.ReviewCreateRequest;
import com.codereview.codereview.review.model.request.ReviewUpdateRequest;
import com.codereview.codereview.review.model.response.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final BoardRepository boardRepository;

    @Transactional
    public ResponseEntity createCodeReview(ReviewCreateRequest request, User user) {
        boardRepository.save(request.toBoard(user));
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity updateCodeReview(Long id, ReviewUpdateRequest request, User user) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> {
                    throw new BoardExceptionImpl(BoardErrorType.BOARD_NOT_FOUND);
                });

        checkUser(board.getUser(), user);

        board.updateCodeReview(
                request.title(),
                request.code(),
                request.question(),
                request.problem()
        );

        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity deleteCodeReview(Long id, User user) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> {
                    throw new BoardExceptionImpl(BoardErrorType.BOARD_NOT_FOUND);
                });

        checkUser(board.getUser(), user);

        boardRepository.delete(board);
        return ResponseEntity.ok().build();
    }

    private void checkUser(User codereviewUser, User user) {
        if (codereviewUser.getId() != user.getId()) {
            //TODO: 예외처리
        }
    }

    public ResponseEntity selectCodeReview() {

        List<ReviewResponse> reviewResponses = boardRepository.findAll().stream()
                .map(board -> {
                    return new ReviewResponse(
                            board.getId(),
                            board.getTitle(),
                            board.getProblem(),
                            board.getQuestion(),
                            board.getCategory(),
                            board.getStatus(),
                            0, //TODO: 처리해야함.
                            board.getCreatedAt()
                    );
                })
                .toList();

        return ResponseEntity.ok()
                .body(reviewResponses);
    }

    public ResponseEntity selectOneCodeReview(Long id) {
        ReviewResponse reviewResponse = boardRepository.findById(id).stream()
                .map(board -> {
                    return new ReviewResponse(
                            board.getId(),
                            board.getTitle(),
                            board.getProblem(),
                            board.getQuestion(),
                            board.getCategory(),
                            board.getStatus(),
                            0, //TODO: 처리해야함.
                            board.getCreatedAt()
                    );
                })
                .findAny().orElseThrow(
                        //TODO:예외처리
                );

        return ResponseEntity.ok()
                .body(reviewResponse);
    }
}
