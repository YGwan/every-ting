package com.everyTing.team.common.notificationForm;

import com.everyTing.core.notification.domain.NotificationType;

public class TeamMemberLeftNotificationForm extends NotificationForm {

    String title;
    String body;
    Long targetId;
    NotificationType notificationType;

    private TeamMemberLeftNotificationForm(String title, String body, Long targetId,
        NotificationType notificationType) {
        this.title = title;
        this.body = body;
        this.targetId = targetId;
        this.notificationType = notificationType;
    }

    public static TeamMemberLeftNotificationForm of(Long targetId, String teamName, String memberUsername) {
        return new TeamMemberLeftNotificationForm(
            "사라진 내 팀원.. 😢",
            teamName + "팀에서 " + memberUsername + "님이 나가셨습니다.",
            targetId,
            NotificationType.TEAM_MEMBER_LEFT
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
    public Long getTargetId() {
        return targetId;
    }

    @Override
    public NotificationType getNotificationType() {
        return notificationType;
    }
}
