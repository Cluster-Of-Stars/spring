package com.codereview.codereview.global.error.exception;

import com.codereview.codereview.global.error.errortype.BoardErrorType;
import com.codereview.codereview.global.error.errortype.UserErrorType;
import org.springframework.web.client.HttpStatusCodeException;

public class UserExceptionImpl  extends HttpStatusCodeException {

    public UserExceptionImpl(UserErrorType errorType) {
        super(errorType.getStatusCode(), errorType.getErrorCode());
    }

}
