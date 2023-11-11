package com.everyTing.notification.service;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.notification.domain.Notification;
import com.everyTing.notification.dto.response.NotificationResponse;
import com.everyTing.notification.repository.NotificationRepository;
import com.everyTing.notification.dto.form.NotificationForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public List<NotificationResponse> findAllNotifications(Long memberId) {
        List<Notification> notifications = notificationRepository.findAllByMemberIdOrderByCreatedAtDesc(memberId);

        return notifications.stream()
                .map(NotificationResponse::from)
                .collect(Collectors.toUnmodifiableList());
    }

    public void addNotification(Long memberId, NotificationForm notificationForm) {
        notificationRepository.save(Notification.of(memberId, notificationForm));
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
