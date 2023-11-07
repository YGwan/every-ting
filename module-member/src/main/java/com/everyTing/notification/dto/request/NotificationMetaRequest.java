package com.everyTing.notification.dto.request;

import lombok.Getter;

@Getter
public class NotificationMetaRequest {

    private String pushToken;

    public NotificationMetaRequest() {
    }

    public NotificationMetaRequest(String pushToken) {
        this.pushToken = pushToken;
    }
}
