package com.everyTing.notification.service;

import com.everyTing.notification.domain.PushToken;
import com.everyTing.notification.dto.validatedRequest.ValidatedPushTokenAddRequest;
import com.everyTing.notification.repository.PushTokenRepository;
import org.springframework.stereotype.Service;

@Service
public class PushTokenService {

    private final PushTokenRepository pushTokenRepository;

    public PushTokenService(PushTokenRepository pushTokenRepository) {
        this.pushTokenRepository = pushTokenRepository;
    }

    public void addPushToken(ValidatedPushTokenAddRequest request) {
        final var pushToken = PushToken.from(request);
        pushTokenRepository.save(pushToken);
    }
}
