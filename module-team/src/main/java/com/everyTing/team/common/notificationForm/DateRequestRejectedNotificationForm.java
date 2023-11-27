package com.everyTing.team.common.notificationForm;

import com.everyTing.core.notification.domain.NotificationType;

public class DateRequestRejectedNotificationForm extends NotificationForm {

    String title;
    String body;
    Long targetId;
    NotificationType notificationType;

    private DateRequestRejectedNotificationForm(String title, String body, Long targetId,
        NotificationType notificationType) {
        this.title = title;
        this.body = body;
        this.targetId = targetId;
        this.notificationType = notificationType;
    }

    public static DateRequestRejectedNotificationForm of(Long requestId, String myTeamName) {
        return new DateRequestRejectedNotificationForm(
            "미팅 요청 거절 😭",
            myTeamName + "팀이 한 미팅 요청이 거절되었습니다.",
            requestId,
            NotificationType.TEAM_DATE_REQUEST_REJECTED
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
