package com.codereview.codereview.review.service;

import com.codereview.codereview.global.error.errortype.RegisterErrorType;
import com.codereview.codereview.global.error.exception.RegisterExceptionImpl;
import com.codereview.codereview.global.model.entity.User;
import com.codereview.codereview.global.repository.SkillRepository;
import com.codereview.codereview.review.model.request.ReviewCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final SkillRepository skillRepository;

    public ResponseEntity createCodeReview(ReviewCreateRequest request, User user) {



        return ResponseEntity.ok().build();
    }

    @Transactional(readOnly = true)
    private List<String> validationSkill(List<String> skills) {

        for (String skill : skills) {
            System.out.println(skill);
            skillRepository.findBySkill(skill).orElseThrow(() -> {
                throw new RegisterExceptionImpl(RegisterErrorType.REGISTER_ERROR);
            });
        }

        return skills;
    }


}
