package com.everyTing.member.controller;

import com.everyTing.core.dto.Response;
import com.everyTing.core.token.data.MemberTokens;
import com.everyTing.core.token.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api/v1/token")
@RestController
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping("/check")
    public Response<Void> tokenCheck(HttpServletRequest request) {
        final String accessToken = tokenService.getAccessTokenFromHeader(request);
        tokenService.validateToken(accessToken);
        return Response.success();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/reissue")
    public Response<MemberTokens> tokenReissue(HttpServletRequest request) {
        final var memberTokens = tokenService.reissue(request);
        return Response.success(memberTokens);
    }
}
