package com.codereview.codereview.review.service;

import com.codereview.codereview.global.error.errortype.BoardErrorType;
import com.codereview.codereview.global.error.exception.BoardExceptionImpl;
import com.codereview.codereview.global.model.entity.Board;
import com.codereview.codereview.global.model.entity.User;
import com.codereview.codereview.global.repository.BoardRepository;
import com.codereview.codereview.global.repository.UserRepository;
import com.codereview.codereview.review.model.request.ReviewCreateRequest;
import com.codereview.codereview.review.model.request.ReviewUpdateRequest;
import com.codereview.codereview.review.model.response.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseEntity createCodeReview(ReviewCreateRequest request, Long userId) {
        System.out.println(request);
        boardRepository.save(request.toBoard(request, getUser(userId)));
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity updateCodeReview(Long boardId, ReviewUpdateRequest request, Long userId) {
        Board board = getBoard(boardId);

        checkUser(board.getUser(), getUser(userId));

        board.updateCodeReview(
                request.title(),
                request.code(),
                request.question(),
                request.problem()
        );

        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity deleteCodeReview(Long boardId, Long userId) {
        Board board = getBoard(boardId);

        checkUser(board.getUser(), getUser(userId));

        boardRepository.delete(board);
        return ResponseEntity.ok().build();
    }

    @Transactional
    private Board getBoard(Long id) {
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

    @Transactional(readOnly = true)
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
