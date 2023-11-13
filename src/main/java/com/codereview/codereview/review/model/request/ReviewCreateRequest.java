package com.codereview.codereview.review.model.request;

import com.codereview.codereview.global.model.entity.Board;
import com.codereview.codereview.global.model.entity.Skill;
import com.codereview.codereview.global.model.entity.User;
import com.codereview.codereview.global.model.type.CodeReviewStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

public record ReviewCreateRequest(
        String title,
        String problem,
        String question,
        List<String> category,
        String code
) {
    public Board toBoard(ReviewCreateRequest req, User user) {
        return Board.builder()
                .user(user)
                .title(req.title())
                .problem(req.problem())
                .question(req.question())
                .code(req.code())
                .views(0)
                .category(req.category())
                .status(CodeReviewStatus.CODE_WAITING)
                .build();
    }
}
