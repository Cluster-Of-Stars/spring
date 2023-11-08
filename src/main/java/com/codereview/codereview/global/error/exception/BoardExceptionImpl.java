package com.codereview.codereview.global.error.exception;

import com.codereview.codereview.global.error.errortype.BoardErrorType;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpStatusCodeException;

public class BoardExceptionImpl extends HttpStatusCodeException {

    public BoardExceptionImpl(BoardErrorType errorType) {
        super(errorType.getStatusCode(), errorType.getErrorCode());
    }

}
