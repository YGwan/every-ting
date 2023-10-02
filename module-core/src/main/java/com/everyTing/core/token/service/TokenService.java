package com.everyTing.core.token.service;

import com.everyTing.core.token.type.MemberTokens;
import com.everyTing.core.token.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Map;

@Service
public class TokenService {

    private final String tokenKey;
    private final int accessTokenExpireTime;
    private final int refreshTokenExpireTime;
    private final String accessTokenCookieKey;
    private final String refreshTokenCookieKey;
    private final Key key;

    public TokenService(
            @Value("${jwt.secretKey}") String secret,
            @Value("${auth.jwt.payload.key:userId}") String tokenKey,
            @Value("${auth.jwt.token.access.ttl.time:1800}") int accessTokenExpireTime,
            @Value("${auth.jwt.token.refresh.ttl.time:259200}") int refreshTokenExpireTime,
            @Value("${auth.jwt.token.access.cookie.key:accessToken}") String accessTokenCookieKey,
            @Value("${auth.jwt.token.refresh.cookie:refreshToken}") String refreshTokenCookieKey
    ) {
        this.key = JwtUtils.createSecretKey(secret);
        this.tokenKey = tokenKey;
        this.accessTokenExpireTime = accessTokenExpireTime;
        this.refreshTokenExpireTime = refreshTokenExpireTime;
        this.accessTokenCookieKey = accessTokenCookieKey;
        this.refreshTokenCookieKey = refreshTokenCookieKey;
    }

    public MemberTokens issue(Long userId) {
        final Map<String, Object> tokenPayload = Map.of(tokenKey, userId);
        final String accessToken = JwtUtils.createToken(key, tokenPayload, accessTokenExpireTime);
        final String refreshToken = JwtUtils.createToken(key, tokenPayload, refreshTokenExpireTime);
        return new MemberTokens(accessToken, refreshToken);
    }

//    public MemberTokens reissue(HttpServletRequest request, HttpServletResponse response) {
//        final String accessToken = getTokenFromCookies(accessTokenCookieKey, request.getCookies());
//        JwtUtils.requireExpired(key, accessToken);
//        final String refreshToken = getTokenFromCookies(refreshTokenCookieKey, request.getCookies());
//        JwtUtils.validate(key, refreshToken);
//
//        final String userEmail = JwtUtils.tokenValue(key, tokenKey, accessToken, true);
//        if (!userEmail.equals(JwtUtils.tokenValue(key, tokenKey, refreshToken))) {
//            throw new TokenException(HttpStatus.UNAUTHORIZED, "Reissue request is invalid");
//        }
//        MemberTokens newTokens = issue(userEmail);
//        return newTokens;
//    }

    public Long memberInfoByAccessToken(String accessToken) {
        final Long userId = JwtUtils.tokenValue(key, tokenKey, accessToken, true);
        return userId;
    }

    public void validateToken(String token) {
        JwtUtils.validate(key, token);
    }
}

