package com.everyTing.core.token.cache;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "memberTokensCache", timeToLive = 259200)
public class MemberTokensCache {

    @Id
    private final Long memberId;

    private final String accessToken;

    private final String refreshToken;

    public MemberTokensCache(Long memberId, String accessToken, String refreshToken) {
        this.memberId = memberId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
