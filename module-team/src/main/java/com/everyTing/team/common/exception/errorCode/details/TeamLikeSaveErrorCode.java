package com.everyTing.team.common.exception.errorCode.details;

import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_010;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_011;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_012;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_026;

import com.everyTing.core.swagger.SwaggerErrorCode;
import com.everyTing.team.common.exception.errorCode.TeamErrorCode;

public enum TeamLikeSaveErrorCode implements SwaggerErrorCode {

    NOT_TEAM_MEMBER(TEAM_010),
    TEAM_LEADER(TEAM_026),
    SAME_GENDER(TEAM_011),
    ALREADY_LIKED(TEAM_012)
    ;

    private TeamErrorCode errorCode;

    TeamLikeSaveErrorCode(TeamErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public TeamErrorCode getErrorCode() {
        return errorCode;
    }
}
