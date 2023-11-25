package com.everyTing.core.token.service;

import com.everyTing.core.token.data.MemberTokens;
import com.everyTing.core.token.exception.TokenException;
import com.everyTing.core.token.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Map;

import static com.everyTing.core.token.errorCode.TokenErrorCode.TOKEN_005;
import static com.everyTing.core.token.errorCode.TokenErrorCode.TOKEN_006;

@Service
public class TokenService {

    private final String tokenKey;
    private final int accessTokenExpireTime;
    private final int refreshTokenExpireTime;
    private final String accessTokenKey;
    private final String refreshTokenKey;
    private final Key key;

    public TokenService(
            @Value("${jwt.secretKey}") String secret,
            @Value("${auth.jwt.payload.key}") String tokenKey,
            @Value("${auth.jwt.token.access.ttl.time}") int accessTokenExpireTime,
            @Value("${auth.jwt.token.refresh.ttl.time}") int refreshTokenExpireTime,
            @Value("${auth.jwt.token.access.key}") String accessTokenKey,
            @Value("${auth.jwt.token.refresh.key}") String refreshTokenKey
    ) {
        this.key = JwtUtils.createSecretKey(secret);
        this.tokenKey = tokenKey;
        this.accessTokenExpireTime = accessTokenExpireTime;
        this.refreshTokenExpireTime = refreshTokenExpireTime;
        this.accessTokenKey = accessTokenKey;
        this.refreshTokenKey = refreshTokenKey;
    }

    public MemberTokens issue(Long memberId) {
        final Map<String, Object> tokenPayload = Map.of(tokenKey, memberId);
        final String accessToken = JwtUtils.createToken(key, tokenPayload, accessTokenExpireTime);
        final String refreshToken = JwtUtils.createToken(key, tokenPayload, refreshTokenExpireTime);
        return new MemberTokens(accessToken, refreshToken);
    }

    public MemberTokens reissue(HttpServletRequest request) {
        final String accessToken = getAccessTokenFromHeader(request);
        JwtUtils.throwIfNotExpired(key, accessToken);

        final String refreshToken = getRefreshTokenFromHeader(request);
        JwtUtils.validate(key, refreshToken);

        final Long memberId = JwtUtils.tokenValue(key, tokenKey, accessToken, true);

        if (!memberId.equals(JwtUtils.tokenValue(key, tokenKey, refreshToken))) {
            throw new TokenException(TOKEN_006);
        }

        return issue(memberId);
    }

    public Long memberInfoByAccessToken(HttpServletRequest request) {
        final String accessToken = getAccessTokenFromHeader(request);
        final Long memberId = JwtUtils.tokenValue(key, tokenKey, accessToken, true);
        return memberId;
    }

    public void validateToken(String token) {
        JwtUtils.validate(key, token);
    }

    public String getAccessTokenFromHeader(HttpServletRequest request) {
        String accessToken = request.getHeader(accessTokenKey);
        if (accessToken == null) {
            throw new TokenException(TOKEN_005);
        }
        return accessToken;
    }

    public String getRefreshTokenFromHeader(HttpServletRequest request) {
        String refreshToken = request.getHeader(refreshTokenKey);
        if (refreshToken == null) {
            throw new TokenException(TOKEN_005);
        }
        return refreshToken;
    }
}

