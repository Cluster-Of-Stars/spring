package com.codereview.codereview.domain.review.model.request;

public record ReviewUpdateRequest(
        String title,
        String problem,
        String question,
        String code
) {
}
