package com.everyTing.core.token.cache;

import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@RedisHash(value = "memberTokens", timeToLive = 259200)
public class MemberTokens {

    @Id
    private final Long memberId;

    private final String accessToken;

    private final String refreshToken;

    public MemberTokens(Long memberId, String accessToken, String refreshToken) {
        this.memberId = memberId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
