package com.everyTing.notification.service;

import com.everyTing.notification.dto.form.PhotoGeneratedCompletedForm;
import com.everyTing.notification.dto.form.PhotoGeneratedErrorForm;
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

    public void errorGeneratedPhotoNotificationSend(Long memberId) {
        final PhotoGeneratedErrorForm form = new PhotoGeneratedErrorForm();
        fcmService.sendNotification(memberId, form);
        notificationService.addNotification(memberId, form);
    }

    public void completedGeneratedPhotoNotificationSend(Long memberId) {
        final PhotoGeneratedCompletedForm form = new PhotoGeneratedCompletedForm();
        fcmService.sendNotification(memberId, form);
        notificationService.addNotification(memberId, form);
    }
}
