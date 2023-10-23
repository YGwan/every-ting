package com.everyTing.team.adapter.out.redis.cache;

import lombok.Getter;

@Getter
public class TeamDateCountCache {

    private Long teamId;
    private Integer count;

    protected TeamDateCountCache() {
    }

    private TeamDateCountCache(Long teamId, Integer count) {
        this.teamId = teamId;
        this.count = count;
    }

    public static TeamDateCountCache from(Long teamId) {
        return new TeamDateCountCache(teamId, 0);
    }

    public void increaseCount() {
        count++;
    }

    @Override
    public String toString() {
        return "TeamDateCountCache{" +
            "teamId=" + teamId +
            ", count=" + count +
            '}';
    }
}
