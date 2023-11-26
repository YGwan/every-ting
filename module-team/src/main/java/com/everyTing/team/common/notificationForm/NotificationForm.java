package com.everyTing.team.common.notificationForm;

import com.everyTing.core.notification.domain.NotificationType;

public abstract class NotificationForm {

    String title;
    String body;
    Long targetId;
    NotificationType notificationType;

    public abstract String getTitle();
    public abstract String getBody();
    public abstract Long getTargetId();
    public abstract NotificationType getNotificationType();
}
