package com.codereview.codereview.domain.auth.model.request;

import jakarta.validation.constraints.Email;

public record EmailCheckRequest(
        @Email
        String email,
        String successKey
) {
}
