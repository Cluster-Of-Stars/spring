package com.codereview.codereview.auth.model.request;

import jakarta.validation.constraints.Email;

import java.util.List;

public record RegisterRequest(
        @Email
        String email,
        String password,
        String nickname,
        String skill,
        String successKey
) {
}
