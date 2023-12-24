package com.codereview.codereview.domain.auth.model.request;

import jakarta.validation.constraints.Email;

public record EmailSendRequest(
        @Email
        String email
) {
}
