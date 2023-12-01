package com.everyTing.core.token.filter;

import com.everyTing.core.token.exception.TokenException;
import com.everyTing.core.token.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TokenFilter extends OncePerRequestFilter {

    public static final String[] APPLY_URL_PATTERNS = new String[]{"/*"};

    public static final List<HttpMethod> ALL_HTTP_METHODS = List.of(
            HttpMethod.GET,
            HttpMethod.HEAD,
            HttpMethod.POST,
            HttpMethod.PUT,
            HttpMethod.DELETE
    );

    public static final Map<String, List<HttpMethod>> EXCLUDE_URL_PATTERNS_WITH_METHODS = new HashMap<>();

    static {
        EXCLUDE_URL_PATTERNS_WITH_METHODS.put("/*/api-docs/**", List.of(HttpMethod.GET));
        EXCLUDE_URL_PATTERNS_WITH_METHODS.put("/swagger-ui/**", List.of(HttpMethod.GET));
        EXCLUDE_URL_PATTERNS_WITH_METHODS.put("/api/*/members/signUp", List.of(HttpMethod.POST));
        EXCLUDE_URL_PATTERNS_WITH_METHODS.put("/api/*/members/info", List.of(HttpMethod.GET));
        EXCLUDE_URL_PATTERNS_WITH_METHODS.put("/api/*/members/*/info", List.of(HttpMethod.GET));
        EXCLUDE_URL_PATTERNS_WITH_METHODS.put("/api/*/members/signIn", List.of(HttpMethod.POST));
        EXCLUDE_URL_PATTERNS_WITH_METHODS.put("/api/*/members/username/check", List.of(HttpMethod.GET));
        EXCLUDE_URL_PATTERNS_WITH_METHODS.put("/api/*/members/kakaoId/check", List.of(HttpMethod.GET));
        EXCLUDE_URL_PATTERNS_WITH_METHODS.put("/api/*/members/password/reset", List.of(HttpMethod.PUT));
        EXCLUDE_URL_PATTERNS_WITH_METHODS.put("/api/*/mail/**", ALL_HTTP_METHODS);
        EXCLUDE_URL_PATTERNS_WITH_METHODS.put("/api/*/token/**", ALL_HTTP_METHODS);
        EXCLUDE_URL_PATTERNS_WITH_METHODS.put("/api/*/notifications", List.of(HttpMethod.POST));
        EXCLUDE_URL_PATTERNS_WITH_METHODS.put("/api/*/generated-photos", List.of(HttpMethod.POST));
        EXCLUDE_URL_PATTERNS_WITH_METHODS.put("/api/*/profile-photos/requests", List.of(HttpMethod.PUT));
        EXCLUDE_URL_PATTERNS_WITH_METHODS.put("/api/*/my/teams/exists", List.of(HttpMethod.GET));
    }

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
        final AntPathMatcher pathMatcher = new AntPathMatcher();
        final var path = request.getRequestURI();
        final var httpMethod = HttpMethod.valueOf(request.getMethod());

        return EXCLUDE_URL_PATTERNS_WITH_METHODS.keySet().stream().anyMatch(key ->
                pathMatcher.match(key, path) && EXCLUDE_URL_PATTERNS_WITH_METHODS.get(key).contains(httpMethod));
    }
}
