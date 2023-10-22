package com.everyTing.team.adapter.in.web.request;

import lombok.Getter;

@Getter
public class TeamRequestSaveRequest {

    private Long fromTeamId;

    private Long toTeamId;

    public TeamRequestSaveRequest() {
    }

    public TeamRequestSaveRequest(Long fromTeamId, Long toTeamId) {
        this.fromTeamId = fromTeamId;
        this.toTeamId = toTeamId;
    }
}
