package com.everyTing.team.common.exception.errorCode.details;

import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_015;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_027;

import com.everyTing.core.swagger.SwaggerErrorCode;
import com.everyTing.team.common.exception.errorCode.TeamErrorCode;

public enum TeamRemoveErrorCode implements SwaggerErrorCode {

    NOT_TEAM_LEADER(TEAM_015),
    TEAM_NOT_EMPTY(TEAM_027)
    ;

    private TeamErrorCode errorCode;

    TeamRemoveErrorCode(TeamErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public TeamErrorCode getErrorCode() {
        return errorCode;
    }
}
