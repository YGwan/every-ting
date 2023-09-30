package com.everyTing.team.common.exception.errorCode;

import com.everyTing.core.exception.errorCode.ServerErrorCode;

public enum TeamServerErrorCode implements ServerErrorCode {

    TSER_001("전공이 빈 값입니다."),
    TSER_002("팀 초대코드가 빈 값입니다."),
    TSER_003("대학교가 빈 값입니다."),
    ;

    private String message;

    TeamServerErrorCode(String message) {
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
