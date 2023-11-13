package com.codereview.codereview.review.controller;

import com.codereview.codereview.global.model.security.AuthPayload;
import com.codereview.codereview.global.model.security.UserAuthentication;
import com.codereview.codereview.review.model.request.ReviewCreateRequest;
import com.codereview.codereview.review.model.request.ReviewUpdateRequest;
import com.codereview.codereview.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/codeReview")
public class ReviewController {

    private final ReviewService reviewServicer;

    @GetMapping
    public ResponseEntity selectCodeReview() {
        return reviewServicer.selectCodeReview();
    }

    @GetMapping("/{id}")
    public ResponseEntity selectOneCodeReview(
            @PathVariable Long id
    ) {
        return reviewServicer.selectOneCodeReview(id);
    }

    @PostMapping
    public ResponseEntity createCodeReview(
            @RequestBody ReviewCreateRequest request,
            @AuthenticationPrincipal AuthPayload userDetails
    ) {
        return reviewServicer.createCodeReview(request, userDetails.userId());
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCodeReview(
            @PathVariable Long id,
            @RequestBody ReviewUpdateRequest request,
            @AuthenticationPrincipal UserAuthentication userDetails
    ) {
        return reviewServicer.updateCodeReview(id, request, userDetails.getPrincipal().userId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCodeReview(
            @PathVariable Long id,
            @AuthenticationPrincipal UserAuthentication userDetails
    ) {
        return reviewServicer.deleteCodeReview(id, userDetails.getPrincipal().userId());
    }

}
