package com.codereview.codereview.auth.model.request;

import jakarta.validation.constraints.Email;

public record EmailSendRequest(
        @Email
        String email
) {
}
