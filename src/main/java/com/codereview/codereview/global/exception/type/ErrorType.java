package com.codereview.codereview.global.exception.type;

import org.springframework.http.HttpStatusCode;

public interface ErrorType {

    HttpStatusCode getStatusCode();
    String getErrorCode();

}
