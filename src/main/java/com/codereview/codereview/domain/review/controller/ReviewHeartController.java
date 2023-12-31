package com.codereview.codereview.domain.review.controller;

import com.codereview.codereview.domain.review.service.ReviewHeartService;
import com.codereview.codereview.global.security.model.AuthPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/heart/codeReview")
public class ReviewHeartController {

    private final ReviewHeartService service;

    @PostMapping("/{id}")
    public ResponseEntity codeReviewHeart(
            @PathVariable Long id,
            @AuthenticationPrincipal AuthPayload userDetails
    ){
        return service.codeReviewHeart(id, userDetails.userId());
    }

}
