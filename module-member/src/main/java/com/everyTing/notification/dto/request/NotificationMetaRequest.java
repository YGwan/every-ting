package com.everyTing.notification.dto.request;

import lombok.Getter;

@Getter
public class NotificationMetaRequest {

    private String pushToken;

    private Boolean notification_enabled;

    public NotificationMetaRequest() {
    }

    public NotificationMetaRequest(String pushToken, Boolean notification_enabled) {
        this.pushToken = pushToken;
        this.notification_enabled = notification_enabled;
    }
}
