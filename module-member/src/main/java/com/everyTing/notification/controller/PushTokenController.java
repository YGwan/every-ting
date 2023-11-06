package com.everyTing.notification.controller;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMember;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.notification.domain.data.FirebaseToken;
import com.everyTing.notification.dto.request.pushTokenRequest;
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
    public Response<Void> pushTokenAdd(@LoginMember LoginMemberInfo memberInfo,
                                       @RequestBody pushTokenRequest request) {
        final var firebaseToken = FirebaseToken.from(request.getFirebaseToken());
        pushTokenService.addPushToken(memberInfo.getId(), firebaseToken);
        return Response.success();
    }
}
