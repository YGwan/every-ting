package com.everyTing.notification.domain;

import com.everyTing.core.domain.CreatedDateFields;
import com.everyTing.notification.dto.form.NotificationForm;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = @Index(name = "index_memberId", columnList = "memberId"))
@Entity
public class Notification extends CreatedDateFields  {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotNull
    private Long memberId;

    @NotNull
    private String body;

    private Notification(Long memberId, String body) {
        this.memberId = memberId;
        this.body = body;
    }

    public static Notification of(Long memberId, NotificationForm form) {
        return new Notification(memberId, form.body());
    }
}
