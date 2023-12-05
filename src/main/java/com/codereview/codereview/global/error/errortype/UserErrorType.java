package com.codereview.codereview.global.error.errortype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;

@RequiredArgsConstructor
@Getter
public enum UserErrorType {

    NOT_USER(HttpStatusCode.valueOf(401),"해당 유저의 게시글이 아닙니다.")
    ;
    private final HttpStatusCode statusCode;
    private final String errorCode;
}
