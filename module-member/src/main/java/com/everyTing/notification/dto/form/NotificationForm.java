package com.everyTing.notification.dto.form;

import com.everyTing.notification.domain.constant.NotificationType;

public interface NotificationForm {

    String title();

    String body();

    NotificationType notificationType();
}
