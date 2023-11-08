package com.everyTing.notification.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;
@Getter

public class NotificationMetaRequest {

    @NotNull
    private String pushToken;

    @NotNull
    private Boolean notificationEnabled;

    public NotificationMetaRequest() {
    }

    public NotificationMetaRequest(String pushToken, Boolean notificationEnabled) {
        this.pushToken = pushToken;
        this.notificationEnabled = notificationEnabled;
    }
}
