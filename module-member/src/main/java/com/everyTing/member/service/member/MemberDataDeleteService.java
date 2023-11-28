package com.everyTing.member.service.member;

import com.everyTing.notification.service.NotificationMetaService;
import com.everyTing.notification.service.NotificationService;
import com.everyTing.photo.service.GeneratedPhotoService;
import com.everyTing.photo.service.PhotoRequestService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class MemberDataDeleteService {

    private final NotificationMetaService notificationMetaService;

    private final NotificationService notificationService;

    private final GeneratedPhotoService generatedPhotoService;

    private final PhotoRequestService photoRequestService;

    public MemberDataDeleteService(NotificationMetaService notificationMetaService, NotificationService notificationService,
                                   GeneratedPhotoService generatedPhotoService, PhotoRequestService photoRequestService) {
        this.notificationMetaService = notificationMetaService;
        this.notificationService = notificationService;
        this.generatedPhotoService = generatedPhotoService;
        this.photoRequestService = photoRequestService;
    }

    @CacheEvict(value = "member", key = "#memberId")
    public void deleteMemberData(Long memberId) {
        notificationMetaService.removeNotificationMetaByMemberId(memberId);
        notificationService.removeAllNotification(memberId);
        generatedPhotoService.removeGeneratedPhoto(memberId);
        photoRequestService.removePhotoRequest(memberId);
    }
}
