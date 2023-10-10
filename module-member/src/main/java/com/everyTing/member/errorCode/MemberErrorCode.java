package com.everyTing.member.errorCode;

import com.everyTing.core.exception.errorCode.ApplicationErrorCode;
import org.springframework.http.HttpStatus;

public enum MemberErrorCode implements ApplicationErrorCode {
    MEMBER_001("유저 이름은 최대 2 ~ 8글자 사이여야 합니다."),
    MEMBER_002("대학교 이메일 값이 올바르지 않습니다."),
    MEMBER_003("대학교 값이 올바르지 않습니다."),
    MEMBER_004("전공 값이 올바르지 않습니다."),
    MEMBER_005("카카오 ID 값이 올바르지 않습니다."),
    MEMBER_006("키 값이 올바르지 않습니다."),
    MEMBER_007("몸무게 값이 올바르지 않습니다."),
    MEMBER_008("패스워드 형식이 올바르지 않습니다.");

    private final String message;
    private final HttpStatus status;

    MemberErrorCode(String message) {
        this.message = message;
        this.status = HttpStatus.BAD_REQUEST;
    }

    MemberErrorCode(String message, HttpStatus status) {
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
