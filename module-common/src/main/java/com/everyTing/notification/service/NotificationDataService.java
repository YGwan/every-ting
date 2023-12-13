package com.everyTing.notification.service;

import com.everyTing.core.notification.form.NotificationForm;
import com.everyTing.notification.service.fcm.FcmService;
import org.springframework.stereotype.Service;

@Service
public class NotificationDataService {

    private final FcmService fcmService;

    private final NotificationService notificationService;

    public NotificationDataService(FcmService fcmService, NotificationService notificationService) {
        this.fcmService = fcmService;
        this.notificationService = notificationService;
    }

    public void sendNotificationAndAddNotification(Long memberId, Long targetId, NotificationForm form) {
        fcmService.sendNotification(memberId, form);
        notificationService.addNotification(memberId, targetId, form);
    }
}
