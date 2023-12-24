package com.codereview.codereview.domain.auth.model.request;

public record LoginRequest(
        String email,
        String password
) {
}
