package com.everyTing.notification.dto.validatedDto;

import com.everyTing.notification.domain.data.PushToken;
import com.everyTing.notification.dto.request.NotificationMetaRequest;
import lombok.Getter;

@Getter
public class ValidatedNotificationMetaRequest {

    private final PushToken pushToken;

    private final Boolean notificationEnabled;

    public ValidatedNotificationMetaRequest(PushToken pushToken, Boolean notificationEnabled) {
        this.pushToken = pushToken;
        this.notificationEnabled = notificationEnabled;
    }

    public static ValidatedNotificationMetaRequest from(NotificationMetaRequest request) {
        return new ValidatedNotificationMetaRequest(
                PushToken.from(request.getPushToken()),
                request.getNotificationEnabled()
        );
    }
}
