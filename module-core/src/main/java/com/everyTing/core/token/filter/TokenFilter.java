package com.everyTing.core.token.filter;

import com.everyTing.core.token.exception.TokenException;
import com.everyTing.core.token.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter {

    public static final String[] APPLY_URL_PATTERNS = new String[]{"/api/v1/members/my/info"};
    public static final String[] EXCLUDE_URL_PATTERNS = new String[]{};

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
}
