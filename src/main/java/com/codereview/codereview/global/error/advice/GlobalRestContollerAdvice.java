package com.codereview.codereview.global.error.advice;

import com.codereview.codereview.global.error.exception.LoginExceptionImpl;
import com.codereview.codereview.global.error.exception.RegisterExceptionImpl;
import com.codereview.codereview.global.model.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;

@RestControllerAdvice
@Slf4j
public class GlobalRestContollerAdvice {


    @ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity<?> httpStatusCodeException(HttpStatusCodeException e) {
        return ResponseEntity.status(e.getStatusCode())
                .body(new ErrorResponse(e.getStatusCode().value(), e.getStatusText()));
    }
}
