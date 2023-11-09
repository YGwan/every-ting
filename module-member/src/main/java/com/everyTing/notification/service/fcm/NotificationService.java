package com.everyTing.notification.service.fcm;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.core.exception.TingServerException;
import com.everyTing.notification.domain.Notification;
import com.everyTing.notification.dto.response.NotificationResponse;
import com.everyTing.notification.dto.validatedDto.ValidatedSendErrorNotificationRequest;
import com.everyTing.notification.repository.NotificationRepository;
import com.everyTing.notification.service.fcm.form.ErrorForm;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.everyTing.notification.errorCode.NotificationErrorCode.*;
import static com.everyTing.notification.errorCode.NotificationServerErrorCode.NSER_002;

@Slf4j
@Service
public class NotificationService {

    private final FcmFormCreator fcmFormCreator;
    private final NotificationRepository notificationRepository;

    public NotificationService(FcmFormCreator fcmFormCreator, NotificationRepository notificationRepository) {
        this.fcmFormCreator = fcmFormCreator;
        this.notificationRepository = notificationRepository;
    }

    @Transactional
    public void sendErrorNotification(ValidatedSendErrorNotificationRequest request) {
        final var errorForm = new ErrorForm(request.getBody());
        final var message = fcmFormCreator.makeMessage(request.getMemberId(), errorForm);
        sendNotification(message);
        notificationRepository.save(Notification.of(request.getMemberId(), errorForm));
    }

    private void sendNotification(Message message) {
        try {
            FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            log.error(e.getMessage());
            throw new TingServerException(NSER_002);
        }
    }

    public List<NotificationResponse> findAllNotifications(Long memberId) {
        final List<Notification> notifications = notificationRepository.findAllByMemberId(memberId).orElseThrow(
                () -> new TingApplicationException(NOTIFICATION_005)
        );

        return notifications.stream()
                .map(NotificationResponse::from)
                .collect(Collectors.toUnmodifiableList());
    }

    @Transactional
    public void removeNotification(Long memberId, Long notificationId) {
        final var notification = notificationRepository.findById(notificationId).orElseThrow(
                () -> new TingApplicationException(NOTIFICATION_006)
        );

        if (!Objects.equals(notification.getMemberId(), memberId)) {
            throw new TingApplicationException(NOTIFICATION_007);
        }

        notificationRepository.delete(notification);
    }
}
