package com.codereview.codereview.domain.comment.controller;

import com.codereview.codereview.domain.comment.service.ReviewCommentHeartService;
import com.codereview.codereview.global.security.model.AuthPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/heart/comment")
public class ReviewCommentHeartController {

    private final ReviewCommentHeartService commentHeartService;

    @PostMapping("/{commentId}/{reviewId}")
    public ResponseEntity reviewCommentHeart(
            @PathVariable Long commentId,
            @PathVariable Long reviewId,
            @AuthenticationPrincipal AuthPayload userDetails
    ) {
        return commentHeartService.reviewCommentHeart(commentId,reviewId, userDetails.userId());
    }

}
