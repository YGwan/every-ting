package com.everyTing.member.controller;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMember;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.core.token.data.MemberTokens;
import com.everyTing.member.domain.data.KakaoId;
import com.everyTing.member.domain.data.Username;
import com.everyTing.member.dto.request.SignInRequest;
import com.everyTing.member.dto.request.SignUpRequest;
import com.everyTing.member.dto.validatedDto.ValidatedSignInRequest;
import com.everyTing.member.dto.validatedDto.ValidatedSignUpRequest;
import com.everyTing.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api/v1/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signUp")
    public Response<MemberTokens> signUp(@RequestBody SignUpRequest request) {
        ValidatedSignUpRequest validRequest = ValidatedSignUpRequest.from(request);
        MemberTokens memberTokens = memberService.addMember(validRequest);
        return Response.success(memberTokens);
    }

    @PostMapping("/signIn")
    public Response<MemberTokens> signIn(@RequestBody SignInRequest request) {
        ValidatedSignInRequest validRequest = ValidatedSignInRequest.from(request);
        MemberTokens memberTokens = memberService.signIn(validRequest);
        return Response.success(memberTokens);
    }


    @GetMapping("/username/check")
    public Response<Boolean> usernameCheck(@RequestParam String username) {
        boolean isExistUsername = memberService.existsMemberByUsername(Username.from(username));
        return Response.success(isExistUsername);
    }

    @GetMapping("/kakaoId/check")
    public Response<Boolean> kakaoIdCheck(@RequestParam String kakaoId) {
        boolean isExistUsername = memberService.existsMemberByKakaoId(KakaoId.from(kakaoId));
        return Response.success(isExistUsername);
    }

    @GetMapping("/token/reissue")
    public Response<MemberTokens> reissueToken(HttpServletRequest request) {
        MemberTokens memberTokens = memberService.reissueToken(request);
        return Response.success(memberTokens);
    }
}
