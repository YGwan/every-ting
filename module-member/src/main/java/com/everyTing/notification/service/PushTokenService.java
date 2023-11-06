package com.everyTing.notification.service;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.notification.domain.PushToken;
import com.everyTing.notification.domain.data.DeviceToken;
import com.everyTing.notification.repository.PushTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.everyTing.notification.errorCode.NotificationErrorCode.NOTIFICATION_002;

@Service
public class PushTokenService {

    private final PushTokenRepository pushTokenRepository;

    public PushTokenService(PushTokenRepository pushTokenRepository) {
        this.pushTokenRepository = pushTokenRepository;
    }

    @Transactional
    public void addPushToken(Long memberId, DeviceToken deviceToken) {
        final var pushToken = PushToken.of(memberId, deviceToken);
        pushTokenRepository.save(pushToken);
    }

    @Transactional
    public void modifyDeviceToken(Long memberId, DeviceToken deviceToken) {
        final var pushToken = getPushTokenByMemberId(memberId);
        pushToken.modifyDeviceToken(deviceToken);
    }

    public DeviceToken findDeviceTokenByMemberId(Long memberId) {
        final var pushToken = getPushTokenByMemberId(memberId);
        return pushToken.getDeviceToken();
    }

    @Transactional
    public void removePushTokenById(Long memberId) {
        final var pushToken = getPushTokenByMemberId(memberId);
        pushTokenRepository.delete(pushToken);
    }

    private PushToken getPushTokenByMemberId(Long memberId) {
        return pushTokenRepository.findPushTokenByMemberId(memberId).orElseThrow(
                () -> new TingApplicationException(NOTIFICATION_002)
        );
    }
}
