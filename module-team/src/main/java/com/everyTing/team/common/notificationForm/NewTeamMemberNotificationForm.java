package com.everyTing.team.common.notificationForm;

import com.everyTing.core.notification.domain.NotificationType;

public class NewTeamMemberNotificationForm extends NotificationForm {

    String title;
    String body;
    Long targetId;
    NotificationType notificationType;

    private NewTeamMemberNotificationForm(String title, String body, Long targetId,
        NotificationType notificationType) {
        this.title = title;
        this.body = body;
        this.targetId = targetId;
        this.notificationType = notificationType;
    }

    public static NewTeamMemberNotificationForm of(Long teamId, String myTeamName, String memberUsername) {
        return new NewTeamMemberNotificationForm(
            "새로운 팀원 소식이 도착했습니다 🙊",
            memberUsername + "님이 " + myTeamName + "팀 팀원이 되었습니다.",
            teamId,
            NotificationType.NEW_TEAM_MEMBER
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
