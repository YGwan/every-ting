package com.everyTing.notification.controller;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMember;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.notification.dto.request.NotificationMetaRequest;
import com.everyTing.notification.service.NotificationMetaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/notification/meta")
@RestController
public class NotificationMetaController {

    private final NotificationMetaService notificationMetaService;

    public NotificationMetaController(NotificationMetaService notificationMetaService) {
        this.notificationMetaService = notificationMetaService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Response<Void> notificationMetaAdd(@LoginMember LoginMemberInfo memberInfo,
                                              @RequestBody NotificationMetaRequest request) {
        notificationMetaService.addNotificationMeta(memberInfo.getId(), request);
        return Response.success();
    }

    @PutMapping
    public Response<Void> pushTokenModify(@LoginMember LoginMemberInfo memberInfo,
                                          @RequestBody NotificationMetaRequest request) {
        notificationMetaService.modifyPushToken(memberInfo.getId(), request.getPushToken());
        return Response.success();
    }

    @DeleteMapping
    public Response<Void> notificationMetaRemove(@LoginMember LoginMemberInfo memberInfo) {
        notificationMetaService.removeNotificationMetaByMemberId(memberInfo.getId());
        return Response.success();
    }
}
