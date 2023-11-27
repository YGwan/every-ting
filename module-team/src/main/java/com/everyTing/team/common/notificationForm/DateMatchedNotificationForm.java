package com.everyTing.team.common.notificationForm;

import com.everyTing.core.notification.domain.NotificationType;

public class DateMatchedNotificationForm extends NotificationForm {

    String title;
    String body;
    Long targetId;
    NotificationType notificationType;

    private DateMatchedNotificationForm(String title, String body, Long targetId,
        NotificationType notificationType) {
        this.title = title;
        this.body = body;
        this.targetId = targetId;
        this.notificationType = notificationType;
    }

    public static DateMatchedNotificationForm of(Long dateId, String myTeamName, String otherTeamName) {
        return new DateMatchedNotificationForm(
            "새로운 미팅이 도착했습니다 💌",
            "나의 " + myTeamName + "팀과 " + otherTeamName + "팀이 미팅에 매칭되었습니다.",
            dateId,
            NotificationType.TEAM_DATE_MATCHED
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
