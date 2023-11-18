package com.everyTing.notification.service.fcm;

import com.everyTing.notification.service.NotificationMetaService;
import com.everyTing.core.notification.form.NotificationForm;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

@Service
public class FcmFormCreator {

    private final NotificationMetaService notificationMetaService;

    public FcmFormCreator(NotificationMetaService notificationMetaService) {
        this.notificationMetaService = notificationMetaService;
    }

    public Message makeMessage(Long memberId, NotificationForm form) {
        final var pushToken = notificationMetaService.findPushTokenByMemberId(memberId);
        return Message.builder()
                .setToken(pushToken.getValue())
                .setNotification(makeNotification(form.title(), form.body()))
                .build();
    }

    private static Notification makeNotification(String title, String body) {
        return Notification.builder().setTitle(title)
                .setBody(body).build();
    }
}
