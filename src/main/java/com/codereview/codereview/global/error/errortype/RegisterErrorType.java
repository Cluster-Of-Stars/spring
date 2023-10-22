package com.codereview.codereview.global.error.errortype;

import com.codereview.codereview.global.model.error.ErrorResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
@Getter
public enum RegisterErrorType {
    EMAIL_SEND_ERROR(new ErrorResponse(401,"A001")),
    EMAIL_CHECK_ERROR(new ErrorResponse(401,"A002")),
    REGISTER_ERROR(new ErrorResponse(401,"A003"))
    ;

    private final ErrorResponse errorCode;
}
