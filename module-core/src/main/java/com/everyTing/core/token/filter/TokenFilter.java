package com.everyTing.core.token.filter;

import com.everyTing.core.token.exception.TokenException;
import com.everyTing.core.token.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
public class TokenFilter extends OncePerRequestFilter {

    public static final String[] APPLY_URL_PATTERNS = new String[]{
            "/api/v1/members/my/info",
            "/api/v1/members/password/check",
            "/api/v1/members/username/modify",
            "/api/v1/members/kakaoId/modify",
            "/api/v1/members/password/modify",
            "/api/v1/members",
            "/api/v1/notifications/*",
            "/api/v1/notification-metas/*",
            "/api/v1/generated-photos/*",
            "/api/v1/profile-photos/*"
    };

    public static final String[] EXCLUDE_URL_PATTERNS = new String[]{
            "/api/v1/members/signUp",
            "api/v1/members/info",
            "/api/v1/members/signIn",
            "/api/v1/members/username/check",
            "/api/v1/members/kakaoId/check",
            "/api/v1/members/password/reset",
            "/api/v1/mail/*",
            "/api/v1/token/*",
    };

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenFilter.class);

    private final TokenService tokenService;

    public TokenFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String accessToken = tokenService.getAccessTokenFromHeader(request);
            tokenService.validateToken(accessToken);
        } catch (TokenException e) {
            LOGGER.info("token invalidate");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getOutputStream().write(e.getMessage().getBytes());
            return;
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        String method = request.getMethod();

        String path1 = "/api/v1/notifications";
        String path2 = "/api/v1/generated-photos";
        String path3 = "/api/v1/profile-photos/requests";

        if (HttpMethod.POST.name().equals(method) && path1.equals(path)) {
            return true;
        }

        if (HttpMethod.POST.name().equals(method) && path2.equals(path)) {
            return true;
        }

        if (HttpMethod.PUT.name().equals(method) && path3.equals(path)) {
            return true;
        }

        return Arrays.stream(EXCLUDE_URL_PATTERNS).anyMatch(path::startsWith);
    }
}
