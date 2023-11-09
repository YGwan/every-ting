package com.everyTing.team.common.exception.errorCode.details;

import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_015;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_019;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_020;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_021;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_022;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_023;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_024;

import com.everyTing.core.swagger.SwaggerErrorCode;
import com.everyTing.team.common.exception.errorCode.TeamErrorCode;

public enum TeamDateSaveErrorCode implements SwaggerErrorCode {

    INVALID_REQUEST(TEAM_019),
    NOT_TEAM_LEADER(TEAM_015),
    ALREADY_MATCHED(TEAM_020),
    MY_TEAM_NOT_FULL(TEAM_021),
    OTHER_TEAM_NOT_FULL(TEAM_022),
    MY_TEAM_NOT_AVAILABLE_DATE(TEAM_023),
    OTHER_TEAM_NOT_AVAILABLE_DATE(TEAM_024)
    ;

    private TeamErrorCode errorCode;

    TeamDateSaveErrorCode(TeamErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public TeamErrorCode getErrorCode() {
        return errorCode;
    }
}
