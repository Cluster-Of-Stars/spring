package com.codereview.codereview.global.error.exception;

import com.codereview.codereview.auth.model.request.RegisterRequest;
import com.codereview.codereview.global.error.errortype.RegisterErrorType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@Getter
public class RegisterExceptionImpl extends RuntimeException{

    private final RegisterErrorType errorType;

}
