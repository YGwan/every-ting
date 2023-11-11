package com.everyTing.member.service;

import com.everyTing.notification.service.NotificationMetaService;
import com.everyTing.notification.service.NotificationService;
import com.everyTing.photo.service.GeneratedPhotoService;
import com.everyTing.photo.service.PhotoRequestService;
import org.springframework.stereotype.Service;

@Service
public class MemberDeleteApi {

    private final NotificationMetaService notificationMetaService;

    private final NotificationService notificationService;

    private final GeneratedPhotoService generatedPhotoService;

    private final PhotoRequestService photoRequestService;

    public MemberDeleteApi(NotificationMetaService notificationMetaService, NotificationService notificationService,
                           GeneratedPhotoService generatedPhotoService, PhotoRequestService photoRequestService) {
        this.notificationMetaService = notificationMetaService;
        this.notificationService = notificationService;
        this.generatedPhotoService = generatedPhotoService;
        this.photoRequestService = photoRequestService;
    }

    public void deleteMemberApi(Long memberId) {
        notificationMetaService.removeNotificationMetaByMemberId(memberId);
        notificationService.removeAllNotification(memberId);
        generatedPhotoService.removeGeneratedPhoto(memberId);
        photoRequestService.removePhotoRequest(memberId);
    }
}
