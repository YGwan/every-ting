package com.everyTing.member.controller;

import com.everyTing.core.dto.Response;
import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.core.resolver.LoginMember;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.core.token.data.MemberTokens;
import com.everyTing.member.domain.data.KakaoId;
import com.everyTing.member.domain.data.Username;
import com.everyTing.member.dto.request.AuthCodeSendForSignUpRequest;
import com.everyTing.member.dto.request.SignInRequest;
import com.everyTing.member.dto.request.SignUpAuthCodeValidateRequest;
import com.everyTing.member.dto.request.SignUpRequest;
import com.everyTing.member.dto.response.MemberInfoResponse;
import com.everyTing.member.dto.validatedDto.ValidatedAuthCodeSendForSignUpRequest;
import com.everyTing.member.dto.validatedDto.ValidatedSignInRequest;
import com.everyTing.member.dto.validatedDto.ValidatedSignUpRequest;
import com.everyTing.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.everyTing.member.errorCode.MemberErrorCode.MEMBER_010;

@RequestMapping("/api/v1/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/my/info")
    public Response<MemberInfoResponse> MyInfoDetails(@LoginMember LoginMemberInfo memberInfo) {
        final Long memberId = memberInfo.getId();
        final var memberInfoResponse = memberService.findMemberInfo(memberId);
        return Response.success(memberInfoResponse);
    }

    @GetMapping("/{memberId}/info")
    public Response<MemberInfoResponse> MemberInfoDetails(@PathVariable Long memberId) {
        final var myInfoResponse = memberService.findMemberInfo(memberId);
        return Response.success(myInfoResponse);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signUp")
    public Response<MemberTokens> signUp(@RequestBody SignUpRequest request) {
        final var validRequest = ValidatedSignUpRequest.from(request);
        final var memberTokens = memberService.signUp(validRequest);
        return Response.success(memberTokens);
    }

    @PostMapping("/signIn")
    public Response<MemberTokens> signIn(@RequestBody SignInRequest request) {
        final MemberTokens memberTokens;
        try {
            final var validRequest = ValidatedSignInRequest.from(request);
            memberTokens = memberService.signIn(validRequest);
        } catch (TingApplicationException e) {
            throw new TingApplicationException(MEMBER_010);
        }

        return Response.success(memberTokens);
    }

    @GetMapping("/username/check")
    public Response<Void> usernameCheck(@RequestParam String username) {
        memberService.throwIfExistUsername(Username.from(username));
        return Response.success();
    }

    @GetMapping("/kakaoId/check")
    public Response<Void> kakaoIdCheck(@RequestParam String kakaoId) {
        memberService.throwIfExistKakaoId(KakaoId.from(kakaoId));
        return Response.success();
    }

    @PostMapping("/signUp/email/auth/send")
    public Response<Void> authCodeSendForSignUp(@RequestBody AuthCodeSendForSignUpRequest request) {
        final var validatedRequest = ValidatedAuthCodeSendForSignUpRequest.from(request);
        memberService.sendAuthCodeForSignUp(validatedRequest);
        return Response.success();
    }

    @PostMapping("/email/auth/verify")
    public Response<Void> SignUpAuthCodeValidate(@RequestBody SignUpAuthCodeValidateRequest request) {
        memberService.validateEmailAuthCode(request.getEmail(), request.getAuthCode());
        return Response.success();
    }

    @PutMapping("/username/modify")
    public Response<Void> usernameModify(@LoginMember LoginMemberInfo memberInfo,
                                         @RequestParam String username) {
        final var newValidatedUsername = Username.from(username);
        memberService.modifyUsername(memberInfo.getId(), newValidatedUsername);
        return Response.success();
    }

    @GetMapping("/token/reissue")
    public Response<MemberTokens> TokenReissue(HttpServletRequest request) {
        final var memberTokens = memberService.reissueToken(request);
        return Response.success(memberTokens);
    }
}
