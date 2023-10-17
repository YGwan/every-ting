package com.everyTing.cache;

import com.everyTing.core.exception.TingApplicationException;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.Duration;
import java.time.LocalTime;

import static com.everyTing.member.errorCode.MemberErrorCode.MEMBER_011;
import static com.everyTing.member.errorCode.MemberErrorCode.MEMBER_012;

@Getter
@RedisHash(value = "EmailAuthCodeCache", timeToLive = 60 * 5)
public class EmailAuthCodeCache {

    @Id
    private final String email;
    private final String authCode;
    private final LocalTime createdTime;

    public EmailAuthCodeCache(String email, String authCode, LocalTime createdTime) {
        this.email = email;
        this.authCode = authCode;
        this.createdTime = createdTime;
    }

    public void checkValidTime(long mailValidTime) {
        long afterSeconds = Duration.between(createdTime, LocalTime.now()).getSeconds();
        if (afterSeconds > mailValidTime) {
            throw new TingApplicationException(MEMBER_011);
        }
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
