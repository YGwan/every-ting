package com.everyTing.team.common.exception.errorCode.details;

import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_011;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_014;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_015;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_016;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_017;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_018;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_019;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_020;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_021;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_022;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_023;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_024;

import com.everyTing.core.swagger.SwaggerErrorCode;
import com.everyTing.team.common.exception.errorCode.TeamErrorCode;

public enum TeamRequestSaveErrorCode implements SwaggerErrorCode {

    SAME_GENDER(TEAM_011),
    NOT_AVAILABLE_REQUEST(TEAM_014),
    ALREADY_REQUESTED(TEAM_016),
    MY_TEAM_NOT_FULL(TEAM_017),
    OTHER_TEAM_NOT_FULL(TEAM_018),
    ;

    private TeamErrorCode errorCode;

    TeamRequestSaveErrorCode(TeamErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public TeamErrorCode getErrorCode() {
        return errorCode;
    }
}
