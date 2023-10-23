package com.everyTing.team.application.port.out;

public interface TeamDateCachePort {

    void increaseDateCountsWithRedisLocks(Long fromTeamId, Long myTeamId);
}
