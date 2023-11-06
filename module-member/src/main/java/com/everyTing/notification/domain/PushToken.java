package com.everyTing.notification.domain;

import com.everyTing.core.domain.AuditingFields;
import com.everyTing.notification.domain.data.DeviceToken;
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
public class PushToken extends AuditingFields {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long memberId;

    private DeviceToken deviceToken;

    private PushToken(Long memberId, DeviceToken deviceToken) {
        this.memberId = memberId;
        this.deviceToken = deviceToken;
    }

    public static PushToken of(Long memberId, DeviceToken deviceToken) {
        return new PushToken(memberId, deviceToken);
    }

    public void modifyDeviceToken(DeviceToken deviceToken) {
        this.deviceToken = deviceToken;
    }
}
