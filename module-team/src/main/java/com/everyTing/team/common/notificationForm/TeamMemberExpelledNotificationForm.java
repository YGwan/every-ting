package com.everyTing.team.common.notificationForm;

import com.everyTing.core.notification.domain.NotificationType;

public class TeamMemberExpelledNotificationForm extends NotificationForm {

    String title;
    String body;
    NotificationType notificationType;

    private TeamMemberExpelledNotificationForm(String title, String body,
        NotificationType notificationType) {
        this.title = title;
        this.body = body;
        this.notificationType = notificationType;
    }

    public static TeamMemberExpelledNotificationForm of(String teamName) {
        return new TeamMemberExpelledNotificationForm(
            "사라진 내 팀.. 😢",
            teamName + "팀에서 퇴장 당했습니다.",
            NotificationType.TEAM_MEMBER_EXPELLED
        );
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public NotificationType getNotificationType() {
        return notificationType;
    }
}
