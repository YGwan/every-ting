package com.everyTing.notification.service;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.notification.domain.NotificationMeta;
import com.everyTing.notification.domain.data.PushToken;
import com.everyTing.notification.dto.request.NotificationMetaRequest;
import com.everyTing.notification.dto.validatedDto.ValidatedNotificationMetaRequest;
import com.everyTing.notification.repository.NotificationMetaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.everyTing.notification.errorCode.NotificationErrorCode.NOTIFICATION_002;

@Service
public class NotificationMetaService {

    private final NotificationMetaRepository notificationMetaRepository;

    public NotificationMetaService(NotificationMetaRepository notificationMetaRepository) {
        this.notificationMetaRepository = notificationMetaRepository;
    }

    @Transactional
    public void saveNotificationMeta(Long memberId, ValidatedNotificationMetaRequest request) {
        final Optional<NotificationMeta> notificationMetaOptional = notificationMetaRepository.findByMemberId(memberId);

        if (notificationMetaOptional.isPresent()) {
            final var notificationMeta = notificationMetaOptional.get();
            notificationMeta.modifyPushToken(request.getPushToken());
        } else {
            final var notificationMeta = NotificationMeta.of(memberId, request.getPushToken(), request.getNotificationEnabled());
            notificationMetaRepository.save(notificationMeta);
        }
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
        return notificationMetaRepository.findByMemberId(memberId).orElseThrow(
                () -> new TingApplicationException(NOTIFICATION_002));
    }
}
