package com.codereview.codereview.auth.service;

import com.codereview.codereview.auth.model.request.EmailCheckRequest;
import com.codereview.codereview.auth.model.request.EmailSendRequest;
import com.codereview.codereview.auth.model.request.RegisterRequest;
import com.codereview.codereview.global.error.errortype.RegisterErrorType;
import com.codereview.codereview.global.error.exception.RegisterExceptionImpl;
import com.codereview.codereview.global.model.entity.Skill;
import com.codereview.codereview.global.model.entity.User;
import com.codereview.codereview.global.model.type.Rank;
import com.codereview.codereview.global.repository.UserRepository;
import com.codereview.codereview.global.util.EmailUtil;
import com.codereview.codereview.global.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public ResponseEntity register(RegisterRequest req) {

        String successKey = getSuccessKey(req.email());

        if (!req.successKey().equals(successKey)) {
            throw new RegisterExceptionImpl(RegisterErrorType.REGISTER_ERROR);
        }

        userRepository.save(User.builder()
                        .email(req.email())
                        .userPw(req.password())
                        .rank(Rank.Orion)
                        .skills(getSkills(req.skill()))
                        .nickname(req.nickname())
                .build());

        return ResponseEntity.ok().build();
    }

    public ResponseEntity emailSend(EmailSendRequest req) {

        String randomSuccessKey = emailUtil.randomSuccess();

        emailUtil.send_email(req.email(), randomSuccessKey);
        redisUtil.setString(req.email(), randomSuccessKey, 30, TimeUnit.MINUTES);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity emailCheck(EmailCheckRequest req) {

        String successKey = getSuccessKey(req.email());

        if (!req.successKey().equals(successKey)) {
            throw new RegisterExceptionImpl(RegisterErrorType.EMAIL_CHECK_ERROR);
        }

        redisUtil.setString(req.email(), "", 1 , TimeUnit.MICROSECONDS);

        return ResponseEntity.ok().build();
    }

    private String getSuccessKey(String email) {

        String successKey = redisUtil.getString(email);
        if (Objects.isNull(successKey)) {
            throw new RegisterExceptionImpl(RegisterErrorType.EMAIL_CHECK_ERROR);
        }

        return successKey;
    }

    private List<Skill> getSkills(String skill){
        List<Skill> skills = new ArrayList<>();
        return skills;
    }

}
