package com.everyTing.notification.controller;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMember;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.notification.dto.request.NotificationAddRequest;
import com.everyTing.notification.dto.response.NotificationResponse;
import com.everyTing.notification.service.NotificationDataService;
import com.everyTing.notification.service.NotificationService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/notifications")
@RestController
public class NotificationController {

    private final NotificationService notificationService;
    private final NotificationDataService notificationDataService;

    public NotificationController(NotificationService notificationService, NotificationDataService notificationDataService) {
        this.notificationService = notificationService;
        this.notificationDataService = notificationDataService;
    }

    @GetMapping
    public Response<Slice<NotificationResponse>> notificationList(@LoginMember LoginMemberInfo memberInfo, Pageable pageable) {
        final Slice<NotificationResponse> responses = notificationService.findAllNotifications(memberInfo.getId(), pageable);
        return Response.success(responses);
    }

    @PostMapping
    public Response<Void> notificationAdd(@RequestBody NotificationAddRequest request) {
        final var notificationForm = NotificationAddRequest.convertNotificationForm(request);
        notificationDataService.sendNotificationAndAddNotification(request.getMemberId(), request.getTargetId(), notificationForm);
        return Response.success();
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
