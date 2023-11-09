package com.everyTing.notification.errorCode;

import com.everyTing.core.exception.errorCode.ServerErrorCode;

public enum NotificationServerErrorCode implements ServerErrorCode {
    NSER_001("파이어베이스 서버와 연결이 되지 않습니다."),
    NSER_002("파이어베이스 서버에 메세지를 보낼 수 없습니다."),
    NSER_003("알림 메세지 제목이 올바르지 않습니다."),
    NSER_004("알림 메세지 본문이 올바르지 않습니다."),
    ;

    private String message;

    NotificationServerErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getErrorCode() {
        return this.name();
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
