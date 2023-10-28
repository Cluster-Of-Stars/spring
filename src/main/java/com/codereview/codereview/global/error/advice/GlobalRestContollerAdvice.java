package com.codereview.codereview.global.error.advice;

import com.codereview.codereview.global.error.exception.LoginExceptionImpl;
import com.codereview.codereview.global.error.exception.RegisterExceptionImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalRestContollerAdvice {

    @ExceptionHandler(RegisterExceptionImpl.class)
    public ResponseEntity<?> registerException(RegisterExceptionImpl e) {
        return ResponseEntity.status(e.getErrorType().getErrorCode().statusCode())
                .body(e.getErrorType().getErrorCode());
    }
    @ExceptionHandler(LoginExceptionImpl.class)
    public ResponseEntity<?> loginException(LoginExceptionImpl e) {
        return ResponseEntity.status(e.getErrorType().getErrorCode().statusCode())
                .body(e.getErrorType().getErrorCode());
    }

}
