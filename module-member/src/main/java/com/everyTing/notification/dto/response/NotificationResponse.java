package com.everyTing.notification.dto.response;

import com.everyTing.notification.domain.Notification;
import lombok.Getter;

@Getter
public class NotificationResponse {

    private Long id;

    private String title;

    private String body;

    public NotificationResponse() {
    }

    public NotificationResponse(Long id,String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public static NotificationResponse from(Notification notification) {
        final var title = notification.getTitle().getValue();
        final var body = notification.getBody().getValue();
        return new NotificationResponse(notification.getId(),title, body);
    }
}
