package com.codereview.codereview.domain.review.model.request;

import com.codereview.codereview.global.model.entity.Category;
import com.codereview.codereview.global.model.entity.Review;
import com.codereview.codereview.global.model.entity.User;
import com.codereview.codereview.global.model.entity.type.CodeReviewStatus;

import java.util.ArrayList;
import java.util.List;

public record ReviewCreateRequest(
        String title,
        String problem,
        String question,
        List<String> category,
        String code
) {
    public Review toReview(ReviewCreateRequest req, User user, List<Category> categories) {
        return Review.builder()
                .user(user)
                .title(req.title())
                .problem(req.problem())
                .question(req.question())
                .code(req.code())
                .reviewViews(new ArrayList<>())
                .category(categories)
                .status(CodeReviewStatus.CODE_WAITING)
                .build();
    }
}
