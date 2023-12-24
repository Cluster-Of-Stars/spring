package com.codereview.codereview.global.exception.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;

@RequiredArgsConstructor
@Getter
public enum CodeReviewErrorType implements ErrorType {

    BOARD_NOT_FOUND(HttpStatusCode.valueOf(401), "지정된 게시글을 찾을수 없습니다."),
    CATEGORY_NOT_FOUND(HttpStatusCode.valueOf(401), "올바르지 않는 카테고리 입니다.");
    private final HttpStatusCode statusCode;
    private final String errorCode;
}
