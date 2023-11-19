package com.everyTing.notification.dto.request;

import com.everyTing.notification.dto.notification.Form;
import lombok.Getter;

@Getter
public class NotificationAddRequest {

    private Long memberId;

    private Form notificationForm;

    public NotificationAddRequest() {
    }

    public NotificationAddRequest(Long memberId, Form notificationForm) {
        this.memberId = memberId;
        this.notificationForm = notificationForm;
    }
}
