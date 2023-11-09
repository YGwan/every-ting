package com.everyTing.notification.controller;

import com.everyTing.core.dto.Response;
import com.everyTing.notification.dto.request.SendErrorNotificationRequest;
import com.everyTing.notification.dto.validatedDto.ValidatedSendErrorNotificationRequest;
import com.everyTing.notification.service.fcm.NotificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/notification")
@RestController
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/error/send")
    public Response<Void> sendErrorNotification(@RequestBody SendErrorNotificationRequest request) {
        final var validatedRequest = ValidatedSendErrorNotificationRequest.from(request);
        notificationService.sendErrorNotification(validatedRequest);
        return Response.success();
    }
}
