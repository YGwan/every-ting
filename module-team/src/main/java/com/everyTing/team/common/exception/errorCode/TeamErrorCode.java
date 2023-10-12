package com.everyTing.team.common.exception.errorCode;

import com.everyTing.core.exception.errorCode.ApplicationErrorCode;
import org.springframework.http.HttpStatus;

public enum TeamErrorCode implements ApplicationErrorCode {

    TEAM_001("팀명은 3글자 이상 20글자 이하여야 합니다.", HttpStatus.BAD_REQUEST),
    TEAM_002("팀원은 2명 이상 6명 이하여야 합니다.", HttpStatus.BAD_REQUEST),
    TEAM_003("유효하지 않은 지역입니다.", HttpStatus.BAD_REQUEST),
    TEAM_004("해시태그 내용은 1글자 이상 10글자 이하여야 합니다.", HttpStatus.BAD_REQUEST),
    TEAM_005("이미 다른 팀의 팀장으로 등록되어 있습니다.", HttpStatus.BAD_REQUEST),
    TEAM_006("요청된 정보를 가지는 팀이 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    TEAM_007("이미 팀에 속해 있습니다.", HttpStatus.BAD_REQUEST),
    TEAM_008("팀이 꽉 찼습니다.", HttpStatus.BAD_REQUEST),
    TEAM_009("같은 성별의 팀이 아닙니다.", HttpStatus.BAD_REQUEST),
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
