package com.everyTing.notification.domain;

import com.everyTing.notification.domain.data.Body;
import com.everyTing.notification.domain.data.Title;
import com.everyTing.notification.service.fcm.form.NotificationForm;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = @Index(name = "index_memberId", columnList = "memberId"))
@Entity
public class Notification {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotNull
    private Long memberId;

    private Title title;

    private Body body;

    private Notification(Long memberId, Title title, Body body) {
        this.memberId = memberId;
        this.title = title;
        this.body = body;
    }

    public static Notification of(Long memberId, NotificationForm form) {
        final var title = Title.from(form.title());
        final var body = Body.from(form.body());

        return new Notification(memberId, title, body);
    }
}
