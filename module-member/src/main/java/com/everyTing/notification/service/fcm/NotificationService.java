package com.everyTing.notification.service.fcm;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.notification.domain.Notification;
import com.everyTing.notification.dto.response.NotificationResponse;
import com.everyTing.notification.repository.NotificationRepository;
import com.everyTing.notification.service.fcm.form.PhotoGeneratedErrorForm;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.everyTing.notification.errorCode.NotificationErrorCode.NOTIFICATION_003;
import static com.everyTing.notification.errorCode.NotificationErrorCode.NOTIFICATION_004;

@Slf4j
@Transactional
@Service
public class NotificationService {

    private final FcmFormCreator fcmFormCreator;
    private final NotificationRepository notificationRepository;

    public NotificationService(FcmFormCreator fcmFormCreator, NotificationRepository notificationRepository) {
        this.fcmFormCreator = fcmFormCreator;
        this.notificationRepository = notificationRepository;
    }

    public void sendErrorGeneratedPhotoNotification(Long memberId) {
        final var errorForm = new PhotoGeneratedErrorForm();
        final var message = fcmFormCreator.makeMessage(memberId, errorForm);
        sendNotification(message);
        notificationRepository.save(Notification.of(memberId, errorForm));
    }

    private void sendNotification(Message message) {
        try {
            FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            log.error(e.getMessage());
//            throw new TingServerException(NSER_002);
        }
    }

    @Transactional(readOnly = true)
    public List<NotificationResponse> findAllNotifications(Long memberId) {
        List<Notification> notifications = notificationRepository.findAllByMemberIdOrderByCreatedAtDesc(memberId);

        return notifications.stream()
                .map(NotificationResponse::from)
                .collect(Collectors.toUnmodifiableList());
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
