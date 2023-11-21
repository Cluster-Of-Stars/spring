package com.codereview.codereview.review.model.response;

import com.codereview.codereview.global.model.type.CodeReviewStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ReviewOneResponse(
        Long id,
        String title,
        String problem,
        String question,
        String nickname,
        List<String> category,
        CodeReviewStatus action,
        long heartCount,
        String code,
        LocalDateTime createdAt
) {
}
