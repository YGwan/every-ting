package com.everyTing.notification.domain;

import com.everyTing.notification.domain.data.FirebaseToken;
import com.everyTing.notification.dto.validatedRequest.ValidatedPushTokenAddRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class PushToken {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long memberId;

    private FirebaseToken firebaseToken;

    private PushToken(Long memberId, FirebaseToken firebaseToken) {
        this.memberId = memberId;
        this.firebaseToken = firebaseToken;
    }

    public static PushToken from(ValidatedPushTokenAddRequest request) {
        return new PushToken(
                request.getMemberId(),
                request.getFirebaseToken()
        );
    }
}
