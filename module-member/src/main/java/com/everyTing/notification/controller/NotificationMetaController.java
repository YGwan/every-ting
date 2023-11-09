package com.everyTing.notification.controller;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMember;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.notification.domain.data.PushToken;
import com.everyTing.notification.dto.request.NotificationMetaRequest;
import com.everyTing.notification.dto.validatedDto.ValidatedNotificationMetaRequest;
import com.everyTing.notification.service.NotificationMetaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/notification-metas")
@RestController
public class NotificationMetaController {

    private final NotificationMetaService notificationMetaService;

    public NotificationMetaController(NotificationMetaService notificationMetaService) {
        this.notificationMetaService = notificationMetaService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Response<Void> notificationMetaSave(@LoginMember LoginMemberInfo memberInfo,
                                              @RequestBody NotificationMetaRequest request) {
        final var validatedRequest = ValidatedNotificationMetaRequest.from(request);
        notificationMetaService.saveNotificationMeta(memberInfo.getId(), validatedRequest);
        return Response.success();
    }

    @DeleteMapping
    public Response<Void> notificationMetaRemove(@LoginMember LoginMemberInfo memberInfo) {
        notificationMetaService.removeNotificationMetaByMemberId(memberInfo.getId());
        return Response.success();
    }
}
