package com.codereview.codereview.global.error.exception;

import com.codereview.codereview.global.error.errortype.LoginErrorType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpStatusCodeException;

@Getter
public class LoginExceptionImpl extends HttpStatusCodeException {

    private final LoginErrorType errorType;

    public LoginExceptionImpl(LoginErrorType errorType) {
        super(errorType.getStatusCode(), errorType.getErrorCode());
        this.errorType = errorType;
    }
}
