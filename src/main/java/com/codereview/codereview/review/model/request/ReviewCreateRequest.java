package com.codereview.codereview.review.model.request;

import com.codereview.codereview.global.model.entity.Review;
import com.codereview.codereview.global.model.entity.User;
import com.codereview.codereview.global.model.type.CodeReviewStatus;

import java.util.List;

public record ReviewCreateRequest(
        String title,
        String problem,
        String question,
        List<String> category,
        String code
) {
    public Review toBoard(ReviewCreateRequest req, User user) {
        return Review.builder()
                .user(user)
                .title(req.title())
                .problem(req.problem())
                .question(req.question())
                .code(req.code())
                .views(0L)
                .category(req.category())
                .status(CodeReviewStatus.CODE_WAITING)
                .build();
    }
}
