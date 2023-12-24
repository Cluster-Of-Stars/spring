package com.codereview.codereview.domain.util;

import com.codereview.codereview.global.exception.ErrorStatusExceptionImpl;
import com.codereview.codereview.global.exception.type.CodeReviewCommentErrorType;
import com.codereview.codereview.global.exception.type.CodeReviewErrorType;
import com.codereview.codereview.global.exception.type.UserErrorType;
import com.codereview.codereview.global.model.entity.Category;
import com.codereview.codereview.global.model.entity.Review;
import com.codereview.codereview.global.model.entity.ReviewComment;
import com.codereview.codereview.global.model.entity.User;
import com.codereview.codereview.global.model.repository.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
@RequiredArgsConstructor
public class ServiceUtil {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ReviewCommentRepository commentRepository;
    private final ReviewCommentHeartRepository commentHeartRepository;
    private final ReviewHeartRepository reviewHeartRepository;

    @Transactional
    public Review getReview(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ErrorStatusExceptionImpl(CodeReviewErrorType.BOARD_NOT_FOUND);
                });
    }

    @Transactional
    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ErrorStatusExceptionImpl(UserErrorType.NOT_USER);
                });
    }

    @Transactional
    public ReviewComment getReviewComment(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ErrorStatusExceptionImpl(CodeReviewCommentErrorType.REVIEW_COMMENT_NOT_FOUND);
                });
    }

    @Transactional
    public List<Category> getCategory(List<String> list) {
        List<Category> categoryList = new ArrayList<>();

        for (String category : list) {
            categoryList.add(categoryRepository.findBySkill(category)
                    .orElseThrow(() -> {
                        throw new ErrorStatusExceptionImpl(CodeReviewErrorType.CATEGORY_NOT_FOUND);
                    }));
        }

        return categoryList;
    }

    @Transactional
    public List<String> getStringCategory(List<Category> list) {
        List<String> categoryList = new ArrayList<>();

        for (Category category : list) {
            categoryList.add(category.getSkill());
        }

        return categoryList;
    }

    public void checkUser(User masterUser, Long inputUser) {
        if (masterUser.getId() != inputUser) {
            throw new ErrorStatusExceptionImpl(UserErrorType.NOT_USER);
        }
    }

}
