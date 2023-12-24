package com.codereview.codereview.domain.comment.controller;

import com.codereview.codereview.domain.comment.model.request.ReviewCommentRequest;
import com.codereview.codereview.domain.comment.service.ReviewCommentService;
import com.codereview.codereview.global.security.model.AuthPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/codeReview/comment")
public class ReviewCommentController {

    private final ReviewCommentService commentService;

    @PostMapping("/{reviewId}")
    public ResponseEntity createComment(
            @PathVariable Long reviewId,
            @RequestBody ReviewCommentRequest commentRequest,
            @AuthenticationPrincipal AuthPayload userDetails
    ) {
        return commentService.createComment(commentRequest, reviewId, userDetails.userId());
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity updateComment(
            @PathVariable Long commentId,
            @RequestBody ReviewCommentRequest commentRequest,
            @AuthenticationPrincipal AuthPayload userDetails
    ){
        return commentService.updateComment(commentRequest,commentId, userDetails.userId());
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity deleteComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal AuthPayload userDetails
    ){
        return commentService.deleteComment(commentId, userDetails.userId());
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity selectComment(
            @PathVariable Long reviewId,
            @AuthenticationPrincipal AuthPayload userDetails
    ){
        return commentService.selectComment(reviewId,userDetails.userId());
    }

}
