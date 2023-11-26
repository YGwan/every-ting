package com.everyTing.core.feign.dto;

import com.everyTing.core.notification.domain.NotificationType;
import lombok.Getter;

@Getter
public class NotificationAddRequest {

    private Long memberId;
    private String title;
    private String body;
    private Long targetId;
    private NotificationType notificationType;

    protected NotificationAddRequest() {}

    private NotificationAddRequest(Long memberId, String title, String body,
        Long targetId, NotificationType notificationType) {
        this.memberId = memberId;
        this.title = title;
        this.body = body;
        this.targetId = targetId;
        this.notificationType = notificationType;
    }

    public static NotificationAddRequest of(Long memberId, String title, String body,
        Long targetId, NotificationType notificationType) {
        return new NotificationAddRequest(memberId, title, body, targetId, notificationType);
    }
}
