package com.codereview.codereview.auth.service;

import com.codereview.codereview.auth.model.request.EmailCheckRequest;
import com.codereview.codereview.auth.model.request.EmailSendRequest;
import com.codereview.codereview.auth.model.request.RegisterRequest;
import com.codereview.codereview.global.error.errortype.RegisterErrorType;
import com.codereview.codereview.global.error.exception.RegisterExceptionImpl;
import com.codereview.codereview.global.model.entity.Skill;
import com.codereview.codereview.global.model.entity.User;
import com.codereview.codereview.global.model.type.Rank;
import com.codereview.codereview.global.repository.SkillRepository;
import com.codereview.codereview.global.repository.UserRepository;
import com.codereview.codereview.global.util.EmailUtil;
import com.codereview.codereview.global.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    private final SkillRepository skillRepository;

    @Transactional
    public ResponseEntity register(RegisterRequest req) {

        String successKey = getSuccessKey(req.email());

        if (!req.successKey().equals(successKey)) {
            throw new RegisterExceptionImpl(RegisterErrorType.REGISTER_ERROR);
        }
        redisUtil.setString(req.email(), "", 1, TimeUnit.MICROSECONDS);


        userRepository.save(User.builder()
                .email(req.email())
                .userPw(req.password())
                .rank(Rank.Orion)
                .skills(validationSkill(req.skill()))
                .nickname(req.nickname())
                .build());

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
            throw new RegisterExceptionImpl(RegisterErrorType.EMAIL_CHECK_ERROR);
        }

        return ResponseEntity.ok().build();
    }

    private String getSuccessKey(String email) {

        String successKey = redisUtil.getString(email);
        if (Objects.isNull(successKey)) {
            throw new RegisterExceptionImpl(RegisterErrorType.EMAIL_CHECK_ERROR);
        }

        return successKey;
    }

    @Transactional(readOnly = true)
    private List<String> validationSkill(List<String> skills) {

        for (String skill : skills) {
            skillRepository.findBySkill(skill).orElseThrow(() -> {
                System.out.println("여기서 로그");
                throw new RegisterExceptionImpl(RegisterErrorType.REGISTER_ERROR);
            });
        }

        return skills;
    }

}
