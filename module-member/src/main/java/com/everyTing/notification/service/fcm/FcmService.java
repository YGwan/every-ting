package com.everyTing.notification.service.fcm;

import com.everyTing.notification.dto.form.NotificationForm;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
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

    @Async
    public void sendNotification(Long memberId, NotificationForm form) {
        final var message = fcmFormCreator.makeMessage(memberId, form);

        try {
            FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            log.error(e.getMessage());
//            throw new TingServerException(NSER_002);
        }
    }
}
