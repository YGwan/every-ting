package com.everyTing.notification.dto.request;

import lombok.Getter;

@Getter
public class pushTokenRequest {

    private String firebaseToken;

    public pushTokenRequest() {
    }

    public pushTokenRequest(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }
}
