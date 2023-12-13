package com.everyTing.member.cache;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import static org.springframework.data.redis.core.RedisKeyValueAdapter.EnableKeyspaceEvents.ON_STARTUP;

@Getter
@EnableRedisRepositories(enableKeyspaceEvents = ON_STARTUP)
@RedisHash(value = "EmailAuthCodeCache", timeToLive = 200)
public class EmailAuthCodeCache {

    @Id
    private final String email;
    private final String authCode;

    public EmailAuthCodeCache(String email, String authCode) {
        this.email = email;
        this.authCode = authCode;
    }
}
