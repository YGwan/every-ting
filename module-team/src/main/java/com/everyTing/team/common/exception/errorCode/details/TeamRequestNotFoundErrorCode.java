package com.everyTing.team.common.exception.errorCode.details;

import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_019;

import com.everyTing.core.swagger.SwaggerErrorCode;
import com.everyTing.team.common.exception.errorCode.TeamErrorCode;

public enum TeamRequestNotFoundErrorCode implements SwaggerErrorCode {

    TEAM_REQUEST_NOT_FOUND(TEAM_019);

    private TeamErrorCode errorCode;

    TeamRequestNotFoundErrorCode(TeamErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public TeamErrorCode getErrorCode() {
        return errorCode;
    }
}
