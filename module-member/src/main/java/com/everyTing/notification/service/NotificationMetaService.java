package com.everyTing.notification.service;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.notification.domain.NotificationMeta;
import com.everyTing.notification.domain.data.PushToken;
import com.everyTing.notification.repository.NotificationMetaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.everyTing.notification.errorCode.NotificationErrorCode.NOTIFICATION_002;

@Service
public class NotificationMetaService {

    private final NotificationMetaRepository notificationMetaRepository;

    public NotificationMetaService(NotificationMetaRepository notificationMetaRepository) {
        this.notificationMetaRepository = notificationMetaRepository;
    }

    @Transactional
    public Long addNotificationMeta(Long memberId, String pushTokenData) {
        final var pushToken = PushToken.from(pushTokenData);
        final var notificationMetaData = NotificationMeta.of(memberId, pushToken);
        NotificationMeta notificationMeta = notificationMetaRepository.save(notificationMetaData);
        return notificationMeta.getId();
    }

    @Transactional
    public void modifyPushToken(Long memberId, PushToken pushToken) {
        final var notificationMeta = getNotificationMetaByMemberId(memberId);
        notificationMeta.modifyPushToken(pushToken);
    }

    public PushToken findPushTokenByMemberId(Long memberId) {
        final var pushToken = getNotificationMetaByMemberId(memberId);
        return pushToken.getPushToken();
    }

    @Transactional
    public void removeNotificationMetaByMemberId(Long memberId) {
        final var notificationMeta = getNotificationMetaByMemberId(memberId);
        notificationMetaRepository.delete(notificationMeta);
    }

    private NotificationMeta getNotificationMetaByMemberId(Long memberId) {
        return notificationMetaRepository.findPushTokenByMemberId(memberId).orElseThrow(
                () -> new TingApplicationException(NOTIFICATION_002));
    }
}
