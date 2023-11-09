package com.everyTing.notification.dto.response;

import com.everyTing.notification.domain.Notification;
import lombok.Getter;

@Getter
public class NotificationResponse {

    private Long id;

    private String body;

    public NotificationResponse() {
    }

    public NotificationResponse(Long id, String body) {
        this.id = id;
        this.body = body;
    }

    public static NotificationResponse from(Notification notification) {
        return new NotificationResponse(notification.getId(), notification.getBody());
    }
}
