package com.codereview.codereview.domain.auth.service;

import com.codereview.codereview.domain.auth.model.request.EmailCheckRequest;
import com.codereview.codereview.domain.auth.model.request.EmailSendRequest;
import com.codereview.codereview.domain.auth.model.request.RegisterRequest;
import com.codereview.codereview.domain.util.EmailUtil;
import com.codereview.codereview.domain.util.RedisUtil;
import com.codereview.codereview.global.exception.ErrorStatusExceptionImpl;
import com.codereview.codereview.global.exception.type.RegisterErrorType;
import com.codereview.codereview.global.model.entity.Category;
import com.codereview.codereview.global.model.entity.User;
import com.codereview.codereview.global.model.entity.type.Rank;
import com.codereview.codereview.global.model.repository.CategoryRepository;
import com.codereview.codereview.global.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final EmailUtil emailUtil;
    private final RedisUtil redisUtil;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseEntity register(RegisterRequest req) {

        String successKey = getSuccessKey(req.email());

        if (!req.successKey().equals(successKey)) {
            throw new ErrorStatusExceptionImpl(RegisterErrorType.REGISTER_ERROR);
        }

        userRepository.save(User.builder()
                .email(req.email())
                .userPw(passwordEncoder.encode(req.password()))
                .rank(Rank.Orion)
                .category(validationCategories(req.skill()))
                .nickname(req.nickname())
                .build());

        redisUtil.setString(req.email(), "", 1, TimeUnit.MICROSECONDS);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity emailSend(EmailSendRequest req) {

        String randomSuccessKey = emailUtil.randomSuccess();

        emailUtil.send_email(req.email(), randomSuccessKey);
        redisUtil.setString(req.email(), randomSuccessKey, 5, TimeUnit.MINUTES);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity emailCheck(EmailCheckRequest req) {

        String successKey = getSuccessKey(req.email());

        if (!req.successKey().equals(successKey)) {
            throw new ErrorStatusExceptionImpl(RegisterErrorType.EMAIL_CHECK_ERROR);
        }

        return ResponseEntity.ok().build();
    }

    private String getSuccessKey(String email) {

        String successKey = redisUtil.getString(email);
        if (Objects.isNull(successKey)) {
            throw new ErrorStatusExceptionImpl(RegisterErrorType.EMAIL_CHECK_ERROR);
        }

        return successKey;
    }

    @Transactional(readOnly = true)
    private List<Category> validationCategories(List<String> categories) {

        List<Category> categoryList = new ArrayList<>();

        for (String category : categories) {
            categoryList.add(categoryRepository.findBySkill(category).orElseThrow(() -> {
                throw new ErrorStatusExceptionImpl(RegisterErrorType.REGISTER_ERROR);
            }));
        }

        return categoryList;
    }

}
