package com.everyTing.notification.dto.notification;

import com.everyTing.core.notification.domain.NotificationType;
import com.everyTing.core.notification.form.NotificationForm;

public class Form implements NotificationForm {

    private final String title;
    private final String body;
    private final NotificationType notificationType;

    public Form(String title, String body, NotificationType notificationType) {
        this.title = title;
        this.body = body;
        this.notificationType = notificationType;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public String body() {
        return body;
    }

    @Override
    public NotificationType notificationType() {
        return notificationType;
    }
}
