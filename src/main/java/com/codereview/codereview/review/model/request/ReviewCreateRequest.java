package com.codereview.codereview.review.model.request;

import com.codereview.codereview.global.model.entity.Board;
import com.codereview.codereview.global.model.entity.Skill;
import com.codereview.codereview.global.model.entity.User;
import com.codereview.codereview.global.model.type.CodeReviewStatus;

import java.util.Arrays;

public record ReviewCreateRequest(
        String title,
        String problem,
        String question,
        String category,
        String code
) {

    public Board toBoard(User user){
        return Board.builder()
                .user(user)
                .title(title)
                .problem(problem)
                .question(question)
                .code(code)
                .views(0)
                .category(Arrays.asList(category.split(",")))
                .status(CodeReviewStatus.CODE_WAITING)
                .build();
    }

}
