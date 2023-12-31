package com.codereview.codereview.domain.auth.controller;

import com.codereview.codereview.domain.auth.model.request.EmailCheckRequest;
import com.codereview.codereview.domain.auth.model.request.EmailSendRequest;
import com.codereview.codereview.domain.auth.model.request.RegisterRequest;
import com.codereview.codereview.domain.auth.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/register")
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping
    public ResponseEntity register(
            @RequestBody RegisterRequest req
    ) {
        return registerService.register(req);
    }

    @PostMapping("/emailSend")
    public ResponseEntity emailSend(
            @RequestBody EmailSendRequest req
    ) {
        return registerService.emailSend(req);
    }

    @PostMapping("/emailCheck")
    public ResponseEntity emailCheck(
            @RequestBody EmailCheckRequest req
    ) {
        return registerService.emailCheck(req);
    }

}
