package com.codereview.codereview.global.exception.advice;

import com.codereview.codereview.global.exception.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;

@RestControllerAdvice
@Slf4j
public class GlobalRestControllerAdvice {

    @ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity<?> httpStatusCodeException(HttpStatusCodeException e) {
        return ResponseEntity.status(e.getStatusCode())
                .body(new ErrorResponse(e.getStatusCode().value(), e.getStatusText()));
    }

}
