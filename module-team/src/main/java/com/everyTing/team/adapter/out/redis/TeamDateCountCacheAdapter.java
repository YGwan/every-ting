package com.everyTing.team.adapter.out.redis;

import static com.everyTing.team.common.constraints.TeamConstraints.WEEKLY_DATE_LIMIT;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_023;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_024;
import static com.everyTing.team.common.exception.errorCode.TeamServerErrorCode.TSER_006;
import static com.everyTing.team.common.exception.errorCode.TeamServerErrorCode.TSER_007;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.core.exception.TingServerException;
import com.everyTing.team.adapter.out.redis.cache.TeamDateCountCache;
import com.everyTing.team.adapter.out.redis.repository.TeamDateCountCacheRepository;
import com.everyTing.team.application.port.out.TeamDateCachePort;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class TeamDateCountCacheAdapter implements TeamDateCachePort {

    private final TeamDateCountCacheRepository teamDateCountCacheRepository;
    private final RedissonClient redissonClient;

    public TeamDateCountCacheAdapter(TeamDateCountCacheRepository teamDateCountCacheRepository,
        RedissonClient redissonClient) {
        this.teamDateCountCacheRepository = teamDateCountCacheRepository;
        this.redissonClient = redissonClient;
    }

    @Override
    public void increaseDateCountsWithRedisLocks(Long fromTeamId, Long myTeamId) {
        RLock locks = redissonClient.getMultiLock(
            redissonClient.getLock(teamDateCountCacheRepository.getKey(fromTeamId).toLowerCase()), // redis 에 있는 key 와 중복을 막기 위해 toLowerCase를 합니다.
            redissonClient.getLock(teamDateCountCacheRepository.getKey(myTeamId).toLowerCase())
        );

        try {
            if (!locks.tryLock(5, 10, TimeUnit.SECONDS)) {
                throw new TingServerException(TSER_007);
            }
            increaseDateCountsIfPossible(fromTeamId, myTeamId);
        } catch (InterruptedException e) {
            throw new TingServerException(TSER_006);
        } finally {
            locks.unlock();
        }
    }

    private void increaseDateCountsIfPossible(Long fromTeamId, Long myTeamId) {
        if (!increaseDateCountIfPossible(fromTeamId)) {
            throw new TingApplicationException(TEAM_024);
        }
        if (!increaseDateCountIfPossible(myTeamId)) {
            throw new TingApplicationException(TEAM_023);
        }
    }

    private boolean increaseDateCountIfPossible(Long teamId) {
        TeamDateCountCache fromTeamCache =
            teamDateCountCacheRepository.findByTeamId(teamId)
                                        .orElseGet(() -> TeamDateCountCache.from(teamId));

        boolean increased = fromTeamCache.getCount() < WEEKLY_DATE_LIMIT;
        if (increased){
            fromTeamCache.increaseCount();
        }
        teamDateCountCacheRepository.save(fromTeamCache);

        return increased;
    }
}
