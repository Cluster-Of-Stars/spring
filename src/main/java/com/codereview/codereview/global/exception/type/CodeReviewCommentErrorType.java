package com.codereview.codereview.global.exception.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;

@RequiredArgsConstructor
@Getter
public enum CodeReviewCommentErrorType implements ErrorType {
    REVIEW_COMMENT_NOT_FOUND(HttpStatusCode.valueOf(401), "지정된 댓글을 찾을수 없습니다.");

    private final HttpStatusCode statusCode;
    private final String errorCode;
}
