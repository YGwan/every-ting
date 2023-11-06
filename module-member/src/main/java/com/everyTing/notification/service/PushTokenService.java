package com.everyTing.notification.service;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.notification.domain.PushToken;
import com.everyTing.notification.domain.data.FirebaseToken;
import com.everyTing.notification.repository.PushTokenRepository;
import org.springframework.stereotype.Service;

import static com.everyTing.notification.errorCode.NotificationErrorCode.NOTIFICATION_002;

@Service
public class PushTokenService {

    private final PushTokenRepository pushTokenRepository;

    public PushTokenService(PushTokenRepository pushTokenRepository) {
        this.pushTokenRepository = pushTokenRepository;
    }

    public void addPushToken(Long memberId, FirebaseToken firebaseToken) {
        final var pushToken = PushToken.of(memberId, firebaseToken);
        pushTokenRepository.save(pushToken);
    }

    public FirebaseToken findFireBaseTokenByMemberId(Long memberId) {
        final var pushToken = pushTokenRepository.findPushTokenByMemberId(memberId).orElseThrow(
                () -> new TingApplicationException(NOTIFICATION_002)
        );
        return pushToken.getFirebaseToken();
    }
}
