package com.codereview.codereview.global.exception;

import com.codereview.codereview.global.exception.type.ErrorType;
import org.springframework.web.client.HttpStatusCodeException;

public class ErrorStatusExceptionImpl extends HttpStatusCodeException {

    public ErrorStatusExceptionImpl(ErrorType errorType) {
        super(errorType.getStatusCode(), errorType.getErrorCode());
    }

}
