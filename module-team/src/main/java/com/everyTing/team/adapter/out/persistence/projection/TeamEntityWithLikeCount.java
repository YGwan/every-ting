package com.everyTing.team.adapter.out.persistence.projection;

import com.everyTing.team.adapter.out.persistence.entity.TeamEntity;

public class TeamEntityWithLikeCount {

    private TeamEntity teamEntity;
    private Long likeCount;

    public TeamEntityWithLikeCount(TeamEntity teamEntity, Long likeCount) {
        this.teamEntity = teamEntity;
        this.likeCount = likeCount;
    }

    public TeamEntity getTeamEntity() {
        return teamEntity;
    }

    public Long getLikeCount() {
        return likeCount;
    }
}
