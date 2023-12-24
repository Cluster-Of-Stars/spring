package com.codereview.codereview.global.exception.model.response;

public record ErrorResponse(
        int statusCode,
        String errorCode
) {
}
