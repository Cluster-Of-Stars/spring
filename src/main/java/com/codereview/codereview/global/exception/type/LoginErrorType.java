package com.codereview.codereview.global.exception.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;

@RequiredArgsConstructor
@Getter
public enum LoginErrorType implements ErrorType{

    LOGIN_ERROR(HttpStatusCode.valueOf(401),"로그인에 실패하였습니다.")
    ;
    private final HttpStatusCode statusCode;
    private final String errorCode;
}
