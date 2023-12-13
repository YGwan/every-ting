package com.everyTing.notification.domain;

import com.everyTing.core.domain.CreatedDateFields;
import com.everyTing.core.notification.domain.NotificationType;
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
    private Long targetId;

    @NotNull
    private String body;

    @NotNull
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    public Notification(Long memberId, Long targetId, String body, NotificationType notificationType) {
        this.memberId = memberId;
        this.targetId = targetId;
        this.body = body;
        this.notificationType = notificationType;
    }

    public static Notification of(Long memberId, Long targetId, String body, NotificationType notificationType) {
        return new Notification(memberId, targetId, body, notificationType);
    }
}
