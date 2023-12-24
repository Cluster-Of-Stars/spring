package com.codereview.codereview.domain.review.model.response;

import com.codereview.codereview.global.model.entity.type.CodeReviewStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ReviewOneResponse(
        Long id,
        String title,
        String problem,
        String question,
        List<String> category,
        CodeReviewStatus action,
        Long heartCount,
        Long views,
        String code,
        Boolean heartCheck,
        LocalDateTime createdAt
) {
}
