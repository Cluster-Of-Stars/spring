package com.codereview.codereview.domain.review.controller;

import com.codereview.codereview.domain.review.model.request.ReviewCreateRequest;
import com.codereview.codereview.domain.review.model.request.ReviewUpdateRequest;
import com.codereview.codereview.domain.review.service.ReviewService;
import com.codereview.codereview.global.security.model.AuthPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/codeReview")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/category")
    public ResponseEntity selectCategoryCodeReview(
            @RequestParam Integer size,
            @RequestParam Integer page,
            @RequestParam String category
    ) {
        return reviewService.selectCategoryCodeReview(size, page - 1, category);
    }

    @GetMapping
    public ResponseEntity selectCodeReview(
            @RequestParam Integer size,
            @RequestParam Integer page
    ) {
        return reviewService.selectCodeReview(size, page - 1);
    }

    @GetMapping("/{id}")
    public ResponseEntity selectOneCodeReview(
            @PathVariable Long id,
            @AuthenticationPrincipal AuthPayload userDetails
    ) {
        return reviewService.selectOneCodeReview(id, userDetails.userId());
    }

    @PostMapping
    public ResponseEntity createCodeReview(
            @RequestBody ReviewCreateRequest request,
            @AuthenticationPrincipal AuthPayload userDetails
    ) {
        return reviewService.createCodeReview(request, userDetails.userId());
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCodeReview(
            @PathVariable Long id,
            @RequestBody ReviewUpdateRequest request,
            @AuthenticationPrincipal AuthPayload userDetails
    ) {
        return reviewService.updateCodeReview(id, request, userDetails.userId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCodeReview(
            @PathVariable Long id,
            @AuthenticationPrincipal AuthPayload userDetails
    ) {
        return reviewService.deleteCodeReview(id, userDetails.userId());
    }

    @PostMapping("/success/{id}")
    public ResponseEntity successCodeReview(
            @PathVariable Long id,
            @AuthenticationPrincipal AuthPayload userDetails
    ) {
        return reviewService.successCodeReview(id, userDetails.userId());
    }

}
