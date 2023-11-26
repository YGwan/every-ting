package com.everyTing.team.common.notificationForm;

import com.everyTing.core.notification.domain.NotificationType;

public class DateRequestedNotificationForm extends NotificationForm {

    String title;
    String body;
    Long targetId;
    NotificationType notificationType;

    private DateRequestedNotificationForm(String title, String body, Long targetId,
        NotificationType notificationType) {
        this.title = title;
        this.body = body;
        this.targetId = targetId;
        this.notificationType = notificationType;
    }

    public static DateRequestedNotificationForm of(Long requestId, String myTeamName) {
        return new DateRequestedNotificationForm(
            "미팅 요청이 도착했습니다 💌",
            myTeamName + "팀에 미팅 요청이 왔습니다.",
            requestId,
            NotificationType.TEAM_DATE_REQUESTED
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
