package com.codereview.codereview.global.security.model.request;

public record LoginRequest(
        String email,
        String password
) {
}
