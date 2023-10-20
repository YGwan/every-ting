package com.everyTing.core.token.utils;

import com.everyTing.core.token.exception.TokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

import static com.everyTing.core.token.errorCode.TokenErrorCode.*;

public class JwtUtils {

    private static final String SUBJECT = "user-info";

    public static Key createSecretKey(String secret) {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public static String createToken(Key key, Map<String, Object> payloads, int expireTime) {
        final Date now = new Date();
        final Date expiration = new Date(now.getTime() + Duration.ofSeconds(expireTime).toMillis());
        return Jwts.builder().setHeaderParam(Header.TYPE, Header.JWT_TYPE).setClaims(payloads).setExpiration(expiration).setSubject(SUBJECT).signWith(key).compact();
    }

    public static void throwIfNotExpired(Key key, String token) {
        if (!isExpired(key, token)) {
            throw new TokenException(TOKEN_007);
        }
    }

    public static boolean isExpired(Key key, String token) {
        try {
            parsedTokenBody(key, token);
            return false;
        } catch (ExpiredJwtException e) {
            return true;
        } catch (Exception e) {
            throw new TokenException(TOKEN_002);
        }
    }

    public static void validate(Key key, String token) {
        try {
            parsedTokenBody(key, token);
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            throw new TokenException(TOKEN_003);
        } catch (ExpiredJwtException e) {
            throw new TokenException(TOKEN_001);
        } catch (UnsupportedJwtException e) {
            throw new TokenException(TOKEN_004);
        } catch (Exception e) {
            throw new TokenException(TOKEN_002);
        }
    }

    public static Long tokenValue(Key key, String claimName, String token) {
        return tokenValue(key, claimName, token, false);
    }

    public static Long tokenValue(Key key, String claimName, String token, boolean ignoreExpired) {
        try {
            return parsedTokenBody(key, token).get(claimName, Long.class);
        } catch (ExpiredJwtException e) {
            if (ignoreExpired) {
                return e.getClaims().get(claimName, Long.class);
            }
            throw new TokenException(TOKEN_002);
        }
    }

    private static Claims parsedTokenBody(Key key, String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
}
