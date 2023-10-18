package com.everyTing.team.domain;

import com.everyTing.team.adapter.out.persistence.projection.TeamEntityWithLikeCount;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
public class TeamWithLikeCount {

    private final Team team;
    private final Long likeCount;

    private TeamWithLikeCount(Team team, Long likeCount) {
        this.team = team;
        this.likeCount = likeCount;
    }

    public static TeamWithLikeCount from(TeamEntityWithLikeCount teamEntityWithLikeCount) {
        return new TeamWithLikeCount(
            Team.from(teamEntityWithLikeCount.getTeamEntity()),
            teamEntityWithLikeCount.getLikeCount());
    }
}
