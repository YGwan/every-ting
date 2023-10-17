package com.everyTing.team.adapter.in.web.request;

import lombok.Getter;

@Getter
public class TeamLikeRequest {

    private Long likedTeamId;

    public TeamLikeRequest() {
    }

    public TeamLikeRequest(Long likedTeamId) {
        this.likedTeamId = likedTeamId;
    }
}
