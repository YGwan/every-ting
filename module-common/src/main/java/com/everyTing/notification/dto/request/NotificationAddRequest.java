package com.everyTing.notification.dto.request;

import com.everyTing.core.notification.domain.NotificationType;
import com.everyTing.core.notification.form.NotificationForm;
import lombok.Getter;

@Getter
public class NotificationAddRequest {

    private Long memberId;

    private Long targetId;

    private String title;

    private String body;

    private NotificationType notificationType;

    public NotificationAddRequest() {
    }

    public NotificationAddRequest(Long memberId, Long targetId, String title, String body, NotificationType notificationType) {
        this.memberId = memberId;
        this.targetId = targetId;
        this.title = title;
        this.body = body;
        this.notificationType = notificationType;
    }

    public static NotificationForm convertNotificationForm(NotificationAddRequest request) {
        return new NotificationForm() {
            @Override
            public String title() {
                return request.getTitle();
            }

            @Override
            public String body() {
                return request.getBody();
            }

            @Override
            public NotificationType notificationType() {
                return request.getNotificationType();
            }
        };
    }
}
