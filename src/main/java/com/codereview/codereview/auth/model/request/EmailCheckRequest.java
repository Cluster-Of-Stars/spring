package com.codereview.codereview.auth.model.request;

import jakarta.validation.constraints.Email;

public record EmailCheckRequest(
        @Email
        String email,
        String successKey
) {
}
