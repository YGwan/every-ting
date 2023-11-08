package com.everyTing.notification.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotNull;
@Getter

public class NotificationMetaRequest {

    private String pushToken;

    @NotNull
    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Boolean notificationEnabled = true;

    public NotificationMetaRequest() {
    }

    public NotificationMetaRequest(String pushToken, Boolean notificationEnabled) {
        this.pushToken = pushToken;
        this.notificationEnabled = notificationEnabled;
    }
}
