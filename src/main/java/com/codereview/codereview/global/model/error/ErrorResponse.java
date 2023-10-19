package com.codereview.codereview.global.model.error;

public record ErrorResponse(
        int statusCode,
        String errorCode
) {
}
