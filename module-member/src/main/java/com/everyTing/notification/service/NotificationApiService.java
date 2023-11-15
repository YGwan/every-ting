package com.everyTing.notification.service;

import com.everyTing.notification.dto.form.NotificationForm;
import com.everyTing.notification.service.fcm.FcmService;
import org.springframework.stereotype.Service;

@Service
public class NotificationApiService {

    private final FcmService fcmService;

    private final NotificationService notificationService;

    public NotificationApiService(FcmService fcmService, NotificationService notificationService) {
        this.fcmService = fcmService;
        this.notificationService = notificationService;
    }

    public void sendNotificationAndAddNotification(Long memberId, NotificationForm form) {
        fcmService.sendNotification(memberId, form);
        notificationService.addNotification(memberId, form);
    }
}
