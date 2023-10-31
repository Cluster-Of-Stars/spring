package com.codereview.codereview.review.controller;

import com.codereview.codereview.global.model.entity.User;
import com.codereview.codereview.global.model.entity.UserDetailsImpl;
import com.codereview.codereview.review.model.request.ReviewCreateRequest;
import com.codereview.codereview.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/codeReview")
public class ReviewController {

    private final ReviewService reviewServicer;

    @PostMapping
    public ResponseEntity createCodeReview(
            ReviewCreateRequest request,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return reviewServicer.createCodeReview(request, userDetails.getUser());
    }

}
