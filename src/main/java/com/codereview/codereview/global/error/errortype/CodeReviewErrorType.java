package com.codereview.codereview.global.error.errortype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;

@RequiredArgsConstructor
@Getter
public enum CodeReviewErrorType {

    BOARD_NOT_FOUND(HttpStatusCode.valueOf(401),"지정된 게시글을 찾을수 없습니다.")

;
    private final HttpStatusCode statusCode;
    private final String errorCode;
}
