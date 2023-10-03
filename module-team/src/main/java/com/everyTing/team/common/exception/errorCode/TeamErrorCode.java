package com.everyTing.team.common.exception.errorCode;

import com.everyTing.core.exception.errorCode.ApplicationErrorCode;
import org.springframework.http.HttpStatus;

public enum TeamErrorCode implements ApplicationErrorCode {

    TEAM_001("팀명은 3글자 이상 20글자 이하여야 합니다.", HttpStatus.BAD_REQUEST),
    TEAM_002("팀원은 2명 이상 6명 이하여야 합니다.", HttpStatus.BAD_REQUEST),
    TEAM_003("유효하지 않은 지역입니다.", HttpStatus.BAD_REQUEST),
    TEAM_004("해시태그 내용은 1글자 이상 10글자 이하여야 합니다.", HttpStatus.BAD_REQUEST),
    ;

    private String message;
    private HttpStatus status;

    TeamErrorCode(String message, HttpStatus status) {
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
