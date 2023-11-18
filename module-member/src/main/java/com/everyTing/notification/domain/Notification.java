package com.everyTing.notification.domain;

import com.everyTing.core.domain.CreatedDateFields;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = @Index(name = "notification_index_memberId", columnList = "memberId"))
@Entity
public class Notification extends CreatedDateFields  {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotNull
    private Long memberId;

    @NotNull
    private String body;

    @NotNull
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    private Notification(Long memberId, String body, NotificationType notificationType) {
        this.memberId = memberId;
        this.body = body;
        this.notificationType = notificationType;
    }

    public static Notification of(Long memberId, String body, NotificationType notificationType) {
        return new Notification(memberId, body, notificationType);
    }
}
