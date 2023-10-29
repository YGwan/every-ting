package com.everyTing.team.common.exception.errorCode.details;

import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_008;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_009;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_010;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_013;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_026;

import com.everyTing.core.swagger.SwaggerErrorCode;
import com.everyTing.team.common.exception.errorCode.TeamErrorCode;

public enum TeamMemberSaveErrorCode implements SwaggerErrorCode {

    TEAM_FULL(TEAM_008),
    DIFFERENT_GENDER(TEAM_009),
    ALREADY_MEMBER(TEAM_013),
    ;

    private TeamErrorCode errorCode;

    TeamMemberSaveErrorCode(TeamErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public TeamErrorCode getErrorCode() {
        return errorCode;
    }
}
