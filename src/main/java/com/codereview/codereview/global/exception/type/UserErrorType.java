package com.codereview.codereview.global.exception.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;

@RequiredArgsConstructor
@Getter
public enum UserErrorType implements ErrorType{

    NOT_USER(HttpStatusCode.valueOf(401),"해당 유저의 게시글이 아닙니다.")
    ;
    private final HttpStatusCode statusCode;
    private final String errorCode;
}
