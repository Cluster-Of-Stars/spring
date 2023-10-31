package com.codereview.codereview.review.model.request;

public record ReviewCreateRequest(
        String title,
        String problem,
        String question,
        String category,
        String code
) {
}
