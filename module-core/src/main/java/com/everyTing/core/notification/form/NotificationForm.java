package com.everyTing.core.notification.form;

import com.everyTing.core.notification.domain.NotificationType;

public interface NotificationForm {

    String title();

    String body();

    NotificationType notificationType();
}
