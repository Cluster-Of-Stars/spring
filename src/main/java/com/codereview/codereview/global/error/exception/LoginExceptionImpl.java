package com.codereview.codereview.global.error.exception;

import com.codereview.codereview.global.error.errortype.LoginErrorType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class LoginExceptionImpl extends RuntimeException{

    private final LoginErrorType errorType;

}
