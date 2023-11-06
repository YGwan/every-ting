package com.everyTing.notification.dto.request;

import lombok.Getter;

@Getter
public class pushTokenRequest {

    private String deviceToken;

    public pushTokenRequest() {
    }

    public pushTokenRequest(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
