package com.codereview.codereview.global.error.errortype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
@Getter
public enum RegisterErrorType {
    EMAIL_SEND_ERROR(HttpStatusCode.valueOf(401),"이메일을 보내는 도중 에러가 발생하였습니다."),
    EMAIL_CHECK_ERROR(HttpStatusCode.valueOf(401),"이메일의 인증번호가 맞지 않습니다."),
    REGISTER_ERROR(HttpStatusCode.valueOf(401),"회원가입 도중 에러가 발생하였습니다.")
    ;

    private final HttpStatusCode statusCode;
    private final String errorCode;
}
