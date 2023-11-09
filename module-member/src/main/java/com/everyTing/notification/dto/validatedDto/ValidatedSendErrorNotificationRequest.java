package com.everyTing.notification.dto.validatedDto;

import com.everyTing.notification.domain.data.Body;
import com.everyTing.notification.dto.request.SendErrorNotificationRequest;
import lombok.Getter;

@Getter
public class ValidatedSendErrorNotificationRequest {

    private final Long memberId;

    private final Body body;

    public ValidatedSendErrorNotificationRequest(Long memberId, Body body) {
        this.memberId = memberId;
        this.body = body;
    }

    public static ValidatedSendErrorNotificationRequest from(SendErrorNotificationRequest request) {
        return new ValidatedSendErrorNotificationRequest(
                request.getMemberId(),
                Body.from(request.getBody())
        );
    }
}
