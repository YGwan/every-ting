package com.everyTing.team.adapter.in.web.request;

import lombok.Getter;

@Getter
public class TeamUnlikeRequest {

    private Long likedTeamId;

    public TeamUnlikeRequest() {
    }

    public TeamUnlikeRequest(Long likedTeamId) {
        this.likedTeamId = likedTeamId;
    }
}
