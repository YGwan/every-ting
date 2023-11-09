package com.everyTing.team.common.exception.errorCode.details;

import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_001;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_002;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_003;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_004;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_005;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_007;

import com.everyTing.core.swagger.SwaggerErrorCode;
import com.everyTing.team.common.exception.errorCode.TeamErrorCode;

public enum TeamCreateConstraintErrorCode implements SwaggerErrorCode {

    TEAM_NAME(TEAM_001),
    TEAM_MEMBER_LIMIT(TEAM_002),
    TEAM_REGION(TEAM_003),
    TEAM_HASHTAG_CONTENT(TEAM_004),
    TEAM_HASHTAG_LIMIT(TEAM_007),
    TEAM_LEADER(TEAM_005);

    private TeamErrorCode errorCode;

    TeamCreateConstraintErrorCode(TeamErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public TeamErrorCode getErrorCode() {
        return errorCode;
    }
}
