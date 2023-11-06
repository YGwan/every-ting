package com.everyTing.notification.dto.request;

import lombok.Getter;

@Getter
public class PushTokenAddRequest {

    private Long memberId;

    private String firebaseToken;

    public PushTokenAddRequest() {
    }

    public PushTokenAddRequest(Long memberId, String firebaseToken) {
        this.memberId = memberId;
        this.firebaseToken = firebaseToken;
    }
}
