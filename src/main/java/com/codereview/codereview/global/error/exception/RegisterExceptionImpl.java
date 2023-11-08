package com.codereview.codereview.global.error.exception;

import com.codereview.codereview.auth.model.request.RegisterRequest;
import com.codereview.codereview.global.error.errortype.LoginErrorType;
import com.codereview.codereview.global.error.errortype.RegisterErrorType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;

@Getter
public class RegisterExceptionImpl extends HttpStatusCodeException {

    private final RegisterErrorType errorType;

    public RegisterExceptionImpl(RegisterErrorType errorType) {
        super(errorType.getStatusCode(), errorType.getErrorCode());
        this.errorType = errorType;
    }
}
