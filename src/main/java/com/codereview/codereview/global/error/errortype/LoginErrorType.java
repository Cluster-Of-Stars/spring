package com.codereview.codereview.global.error.errortype;

import com.codereview.codereview.global.model.error.ErrorResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum LoginErrorType {

    LOGIN_ERROR(new ErrorResponse(401,"A005"))
    ;
    private final ErrorResponse errorCode;
}
