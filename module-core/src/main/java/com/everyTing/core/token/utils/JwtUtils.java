package com.everyTing.core.token.utils;

import com.everyTing.core.exception.TingApplicationException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

import static com.everyTing.core.token.errorCode.TokenErrorCode.*;

@Component
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

    public static void requireExpired(Key key, String token) {
        if (isExpired(key, token)) {
            return;
        }
        throw new TingApplicationException(TOKEN_001);
    }

    public static boolean isExpired(Key key, String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
            return false;
        } catch (ExpiredJwtException e) {
            return true;
        } catch (Exception e) {
            throw new TingApplicationException(TOKEN_002);
        }
    }

    public static void validate(Key key, String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            throw new TingApplicationException(TOKEN_003);
        } catch (ExpiredJwtException e) {
            throw new TingApplicationException(TOKEN_001);
        } catch (UnsupportedJwtException e) {
            throw new TingApplicationException(TOKEN_004);
        } catch (Exception e) {
            throw new TingApplicationException(TOKEN_002);
        }
    }

    public static Long tokenValue(Key key, String claimName, String token) {
        return tokenValue(key, token, claimName, false);
    }

    public static Long tokenValue(Key key, String claimName, String token, boolean ignoreExpired) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get(claimName, Long.class);
        } catch (ExpiredJwtException e) {
            if (ignoreExpired) {
                return e.getClaims().get(claimName, Long.class);
            }
            throw new TingApplicationException(TOKEN_002);
        }
    }
}
