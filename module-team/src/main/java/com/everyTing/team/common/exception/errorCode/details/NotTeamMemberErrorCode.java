package com.everyTing.team.common.exception.errorCode.details;

import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_010;

import com.everyTing.core.swagger.SwaggerErrorCode;
import com.everyTing.team.common.exception.errorCode.TeamErrorCode;

public enum NotTeamMemberErrorCode implements SwaggerErrorCode {

    NOT_TEAM_MEMBER(TEAM_010),
    ;

    private TeamErrorCode errorCode;

    NotTeamMemberErrorCode(TeamErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public TeamErrorCode getErrorCode() {
        return errorCode;
    }
}
