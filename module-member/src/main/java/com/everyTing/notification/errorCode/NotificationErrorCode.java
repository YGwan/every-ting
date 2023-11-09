package com.everyTing.notification.errorCode;

import com.everyTing.core.exception.errorCode.ApplicationErrorCode;
import org.springframework.http.HttpStatus;

public enum NotificationErrorCode implements ApplicationErrorCode {
    NOTIFICATION_001("푸시 토큰 값이 올바르지 않습니다."),
    NOTIFICATION_002("푸시 토큰 값이 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    NOTIFICATION_003("사용자의 알림 메세지를 찾을 수 없습니다", HttpStatus.NOT_FOUND),
    NOTIFICATION_004("알림 메세지를 찾을 수 없습니다", HttpStatus.NOT_FOUND),
    NOTIFICATION_005("사용자에 맞는 알림 메세지가 아닙니다.")
    ;

    private final String message;
    private final HttpStatus status;

    NotificationErrorCode(String message) {
        this.message = message;
        this.status = HttpStatus.BAD_REQUEST;
    }

    NotificationErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String getErrorCode() {
        return this.name();
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public HttpStatus getStatus() {
        return this.status;
    }
}

