package com.codereview.codereview.domain.auth.model.request;

import jakarta.validation.constraints.Email;

import java.util.List;

public record RegisterRequest(
        @Email
        String email,
        String password,
        String nickname,
        List<String> skill,
        String successKey
) {
}
