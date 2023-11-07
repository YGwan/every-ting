package com.everyTing.member.dto.request;

import lombok.Getter;

@Getter
public class NotificationInfo {

    private String pushToken;

    private Boolean notificationEnabled;

    public NotificationInfo() {
    }

    public NotificationInfo(String pushToken, Boolean notificationEnabled) {
        this.pushToken = pushToken;
        this.notificationEnabled = notificationEnabled;
    }
}
