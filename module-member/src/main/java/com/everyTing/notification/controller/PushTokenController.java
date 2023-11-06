package com.everyTing.notification.controller;

import com.everyTing.core.dto.Response;
import com.everyTing.notification.dto.request.PushTokenAddRequest;
import com.everyTing.notification.dto.validatedRequest.ValidatedPushTokenAddRequest;
import com.everyTing.notification.service.PushTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/pushToken")
@RestController
public class PushTokenController {

    private final PushTokenService pushTokenService;

    public PushTokenController(PushTokenService pushTokenService) {
        this.pushTokenService = pushTokenService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Response<Void> pushTokenAdd(@RequestBody PushTokenAddRequest request) {
        final var validatedRequest = ValidatedPushTokenAddRequest.from(request);
        pushTokenService.addPushToken(validatedRequest);
        return Response.success();
    }
}
