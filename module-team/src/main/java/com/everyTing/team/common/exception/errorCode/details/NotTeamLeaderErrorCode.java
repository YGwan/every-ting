package com.everyTing.team.common.exception.errorCode.details;

import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_015;

import com.everyTing.core.swagger.SwaggerErrorCode;
import com.everyTing.team.common.exception.errorCode.TeamErrorCode;

public enum NotTeamLeaderErrorCode implements SwaggerErrorCode {

    NOT_TEAM_LEADER(TEAM_015),
    ;

    private TeamErrorCode errorCode;

    NotTeamLeaderErrorCode(TeamErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public TeamErrorCode getErrorCode() {
        return errorCode;
    }
}
