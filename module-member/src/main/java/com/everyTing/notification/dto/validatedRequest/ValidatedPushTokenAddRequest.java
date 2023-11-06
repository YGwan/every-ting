package com.everyTing.notification.dto.validatedRequest;

import com.everyTing.notification.domain.data.FirebaseToken;
import com.everyTing.notification.dto.request.PushTokenAddRequest;
import lombok.Getter;

@Getter
public class ValidatedPushTokenAddRequest {

    private final Long memberId;

    private final FirebaseToken firebaseToken;

    public ValidatedPushTokenAddRequest(Long memberId, FirebaseToken firebaseToken) {
        this.memberId = memberId;
        this.firebaseToken = firebaseToken;
    }

    public static ValidatedPushTokenAddRequest from(PushTokenAddRequest request) {
        return new ValidatedPushTokenAddRequest(
                request.getMemberId(),
                FirebaseToken.from(request.getFirebaseToken())
        );
    }
}
