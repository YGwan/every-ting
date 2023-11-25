package com.everyTing.notification.dto.response;

import com.everyTing.notification.domain.Notification;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NotificationResponse {

    private Long id;

    private Long targetId;

    private String body;

    private LocalDateTime createdAt;

    public NotificationResponse() {
    }

    public NotificationResponse(Long id, Long targetId, String body, LocalDateTime createdAt) {
        this.id = id;
        this.targetId = targetId;
        this.body = body;
        this.createdAt = createdAt;
    }

    public static NotificationResponse from(Notification notification) {
        return new NotificationResponse(
                notification.getId(), notification.getTargetId(), notification.getBody(), notification.getCreatedAt());
    }
}
