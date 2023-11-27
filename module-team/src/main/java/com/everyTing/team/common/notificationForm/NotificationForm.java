package com.everyTing.team.common.notificationForm;

import com.everyTing.core.notification.domain.NotificationType;

public abstract class NotificationForm {

    String title;
    String body;
    Long targetId;
    NotificationType notificationType;

    public abstract String getTitle();
    public abstract String getBody();
    public Long getTargetId() {
        return (long) -1; // targetId 가 불필요한 알림의 경우 -1을 반환
    }
    public abstract NotificationType getNotificationType();
}
