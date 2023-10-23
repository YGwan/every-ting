package com.everyTing.team.adapter.out.redis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.everyTing.team.adapter.out.redis.cache.TeamDateCountCache;
import com.everyTing.team.adapter.out.redis.repository.TeamDateCountCacheRepository;
import com.everyTing.team.application.port.in.command.TeamDateSaveCommand;
import com.everyTing.team.application.port.out.TeamDateCachePort;
import com.everyTing.team.application.service.TeamDateService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Disabled;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TeamDateCountCacheAdapterTest {

    @Autowired
    TeamDateCountCacheRepository teamDateCountCacheRepository;

    @Autowired
    private TeamDateCountCacheAdapter teamDateCountCacheAdapter;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    TeamDateService teamDateService;

    @Autowired
    TeamDateCachePort teamDateCachePort;

    @Disabled
    public void increaseDateCount() throws InterruptedException {
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    teamDateService.saveTeamDate(TeamDateSaveCommand.of(1L, 2L));
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        TeamDateCountCache fromTeamCache = teamDateCountCacheRepository.findByTeamId(1L)
                                                                       .orElse(null);

        assertEquals(3, fromTeamCache.getCount());
    }
}