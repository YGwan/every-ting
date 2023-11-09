package com.everyTing.notification.service.fcm;

import com.everyTing.core.exception.TingServerException;
import com.everyTing.notification.dto.validatedDto.ValidatedSendErrorNotificationRequest;
import com.everyTing.notification.service.fcm.form.ErrorForm;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.everyTing.notification.errorCode.NotificationServerErrorCode.NSER_002;

@Slf4j
@Service
public class NotificationService {

    private final FcmFormCreator fcmFormCreator;

    public NotificationService(FcmFormCreator fcmFormCreator) {
        this.fcmFormCreator = fcmFormCreator;
    }

    public void sendErrorNotification(ValidatedSendErrorNotificationRequest request) {
        final var message = fcmFormCreator.makeMessage(request.getMemberId(), new ErrorForm(request.getBody()));
        sendNotification(message);
    }

    private void sendNotification(Message message) {
        try {
            FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            log.error(e.getMessage());
            throw new TingServerException(NSER_002);
        }
    }
}
