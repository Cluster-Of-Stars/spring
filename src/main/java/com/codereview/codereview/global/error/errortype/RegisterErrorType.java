package com.codereview.codereview.global.error.errortype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
@Getter
public enum RegisterErrorType {
    EMAIL_SEND_ERROR(HttpStatusCode.valueOf(401),"A001"),
    EMAIL_CHECK_ERROR(HttpStatusCode.valueOf(401),"A002"),
    REGISTER_ERROR(HttpStatusCode.valueOf(401),"A003")
    ;

    private final HttpStatusCode statusCode;
    private final String errorCode;
}
