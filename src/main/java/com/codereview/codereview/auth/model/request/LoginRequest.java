package com.codereview.codereview.auth.model.request;

public record LoginRequest(
        String email,
        String password
) {
}
