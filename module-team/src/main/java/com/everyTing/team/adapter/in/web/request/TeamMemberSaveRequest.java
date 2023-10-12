package com.everyTing.team.adapter.in.web.request;

import lombok.Getter;

@Getter
public class TeamMemberSaveRequest {

    private Long teamId;

    public TeamMemberSaveRequest() {
    }

    public TeamMemberSaveRequest(Long teamId) {
        this.teamId = teamId;
    }
}
