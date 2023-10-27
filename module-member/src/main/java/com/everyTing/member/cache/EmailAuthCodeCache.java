package com.everyTing.member.cache;

import com.everyTing.core.exception.TingApplicationException;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import static com.everyTing.member.errorCode.MemberErrorCode.MEMBER_012;
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

    public void checkEmailSame(String email) {
        if (!this.email.equals(email)) {
            throw new TingApplicationException(MEMBER_012);
        }
    }

    public void checkAuthCodeSame(String authCode) {
        if (!this.authCode.equals(authCode)) {
            throw new TingApplicationException(MEMBER_012);
        }
    }
}
