package com.everyTing.notification.dto.request;

import lombok.Getter;

@Getter
public class SendErrorNotificationRequest {

    private Long memberId;

    private String body;

    public SendErrorNotificationRequest() {
    }

    public SendErrorNotificationRequest(Long memberId, String body) {
        this.memberId = memberId;
        this.body = body;
    }
}
