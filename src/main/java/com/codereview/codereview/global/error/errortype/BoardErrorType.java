package com.codereview.codereview.global.error.errortype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;

@RequiredArgsConstructor
@Getter
public enum BoardErrorType {

    BOARD_NOT_FOUND(HttpStatusCode.valueOf(401),"A007")

;
    private final HttpStatusCode statusCode;
    private final String errorCode;
}
