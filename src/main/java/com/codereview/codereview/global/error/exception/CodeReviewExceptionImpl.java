package com.codereview.codereview.global.error.exception;

import com.codereview.codereview.global.error.errortype.CodeReviewErrorType;
import org.springframework.web.client.HttpStatusCodeException;

public class CodeReviewExceptionImpl extends HttpStatusCodeException {

    public CodeReviewExceptionImpl(CodeReviewErrorType errorType) {
        super(errorType.getStatusCode(), errorType.getErrorCode());
    }

}
