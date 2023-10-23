package com.everyTing.team.adapter.out.redis.repository;

import com.everyTing.team.adapter.out.redis.cache.TeamDateCountCache;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class TeamDateCountCacheRepository {

    @Value("${team.date.cache.key}")
    private String key;
    @Value("${team.date.cache.ttl.days}")
    private Integer cacheTtl;

    private final RedisTemplate<String, TeamDateCountCache> teamDateRedisTemplate;

    public TeamDateCountCacheRepository(
        RedisTemplate<String, TeamDateCountCache> teamDateRedisTemplate) {
        this.teamDateRedisTemplate = teamDateRedisTemplate;
    }

    public void save(TeamDateCountCache cache) {
        String key = getKey(cache.getTeamId());
        Long ttl = teamDateRedisTemplate.getExpire(key, TimeUnit.SECONDS);

        if (ttl == -2) { // 존재하지 않는 key 라면
            teamDateRedisTemplate.opsForValue()
                                 .setIfAbsent(key, cache, getTtl());
        } else {
            teamDateRedisTemplate.opsForValue()
                                 .setIfPresent(key, cache, Duration.ofSeconds(ttl));
        }
    }

    public Optional<TeamDateCountCache> findByTeamId(Long teamId) {
        String key = getKey(teamId);
        TeamDateCountCache cache = teamDateRedisTemplate.opsForValue()
                                                        .get(key);
        log.info("get data from redis {}, {}", key, cache);
        return Optional.ofNullable(cache);
    }

    public String getKey(Long teamId) {
        return key + teamId;
    }

    private Duration getTtl() {
        return Duration.ofDays(cacheTtl);
    }
}
