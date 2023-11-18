package com.everyTing.notification.service;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.notification.domain.Notification;
import com.everyTing.notification.dto.form.NotificationForm;
import com.everyTing.notification.dto.response.NotificationResponse;
import com.everyTing.notification.repository.NotificationRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static com.everyTing.notification.errorCode.NotificationErrorCode.NOTIFICATION_003;
import static com.everyTing.notification.errorCode.NotificationErrorCode.NOTIFICATION_004;

@Transactional
@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Transactional(readOnly = true)
    public Slice<NotificationResponse> findAllNotifications(Long memberId, Pageable pageable) {
        Slice<Notification> notifications = notificationRepository.findAllByMemberIdOrderByCreatedAtDesc(memberId, pageable);

        return notifications.map(NotificationResponse::from);
    }

    public void addNotification(Long memberId, NotificationForm notificationForm) {
        notificationRepository.save(Notification.of(memberId, notificationForm.body(), notificationForm.notificationType()));
    }

    public void removeNotification(Long memberId, Long notificationId) {
        final var notification = notificationRepository.findById(notificationId).orElseThrow(
                () -> new TingApplicationException(NOTIFICATION_003)
        );

        if (!Objects.equals(notification.getMemberId(), memberId)) {
            throw new TingApplicationException(NOTIFICATION_004);
        }

        notificationRepository.delete(notification);
    }

    public void removeAllNotification(Long memberId) {
        notificationRepository.deleteAllByMemberId(memberId);
    }
}
