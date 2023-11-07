package com.codereview.codereview.global.error.errortype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;

@RequiredArgsConstructor
@Getter
public enum LoginErrorType {

    LOGIN_ERROR(HttpStatusCode.valueOf(401),"A005")
    ;
    private final HttpStatusCode statusCode;
    private final String errorCode;
}
