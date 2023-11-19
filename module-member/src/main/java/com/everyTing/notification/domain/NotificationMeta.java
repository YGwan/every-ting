package com.everyTing.notification.domain;

import com.everyTing.core.domain.AuditingFields;
import com.everyTing.notification.domain.data.PushToken;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = @Index(name = "notification_meta_index_memberId", columnList = "memberId"))
@Entity
public class NotificationMeta extends AuditingFields {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotNull
    @Column(unique = true)
    private Long memberId;

    private PushToken pushToken;

    @NotNull
    private Boolean notificationEnabled;

    private NotificationMeta(Long memberId, PushToken pushToken, Boolean notificationEnabled) {
        this.memberId = memberId;
        this.pushToken = pushToken;
        this.notificationEnabled = notificationEnabled;
    }

    public static NotificationMeta of(Long memberId, PushToken pushToken, Boolean notificationEnabled) {
        return new NotificationMeta(memberId, pushToken, notificationEnabled);
    }

    public void modifyPushToken(PushToken pushToken) {
        this.pushToken = pushToken;
    }
}
