package com.everyTing.notification.service.fcm;

import com.everyTing.notification.dto.form.NotificationForm;
import com.everyTing.notification.dto.form.PhotoGeneratedErrorForm;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Service
public class FcmService {

    private final FcmFormCreator fcmFormCreator;

    public FcmService(FcmFormCreator fcmFormCreator) {
        this.fcmFormCreator = fcmFormCreator;
    }

    public NotificationForm sendErrorGeneratedPhotoNotification(Long memberId) {
        final var errorForm = new PhotoGeneratedErrorForm();
        final var message = fcmFormCreator.makeMessage(memberId, errorForm);
        sendNotification(message);
        return errorForm;
    }

    private void sendNotification(Message message) {
        try {
            FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            log.error(e.getMessage());
//            throw new TingServerException(NSER_002);
        }
    }
}
