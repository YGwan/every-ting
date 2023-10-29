package com.everyTing.team.common.exception.errorCode.details;

import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_025;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_026;

import com.everyTing.core.swagger.SwaggerErrorCode;
import com.everyTing.team.common.exception.errorCode.TeamErrorCode;

public enum TeamMemberRemoveErrorCode implements SwaggerErrorCode {

    TEAM_LEADER(TEAM_026),
    TEAM_MEMBER_NOT_EXISTS(TEAM_025)
    ;

    private TeamErrorCode errorCode;

    TeamMemberRemoveErrorCode(TeamErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public TeamErrorCode getErrorCode() {
        return errorCode;
    }
}
