package com.everyTing.notification.controller;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMember;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.notification.dto.response.NotificationResponse;
import com.everyTing.notification.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/notifications")
@RestController
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public Response<List<NotificationResponse>> notificationList(@LoginMember LoginMemberInfo memberInfo) {
        final List<NotificationResponse> responses = notificationService.findAllNotifications(memberInfo.getId());
        return Response.success(responses);
    }

    @DeleteMapping("/{notificationId}")
    public Response<Void> notificationRemove(@LoginMember LoginMemberInfo memberInfo,
                                             @PathVariable Long notificationId) {
        notificationService.removeNotification(memberInfo.getId(), notificationId);
        return Response.success();
    }

    @DeleteMapping
    public Response<Void> notificationRemoveAll(@LoginMember LoginMemberInfo memberInfo) {
        notificationService.removeAllNotification(memberInfo.getId());
        return Response.success();
    }
}
