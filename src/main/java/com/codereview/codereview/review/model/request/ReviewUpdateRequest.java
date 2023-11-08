package com.codereview.codereview.review.model.request;

public record ReviewUpdateRequest(
        String title,
        String problem,
        String question,
        String code
) {
}
