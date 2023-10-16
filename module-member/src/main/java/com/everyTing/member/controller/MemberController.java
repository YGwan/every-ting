package com.everyTing.member.controller;

import com.everyTing.core.dto.Response;
import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.core.token.data.MemberTokens;
import com.everyTing.member.domain.data.KakaoId;
import com.everyTing.member.domain.data.Username;
import com.everyTing.member.dto.request.AuthCodeSendRequest;
import com.everyTing.member.dto.request.SignInRequest;
import com.everyTing.member.dto.request.SignUpAuthCodeValidateRequest;
import com.everyTing.member.dto.request.SignUpRequest;
import com.everyTing.member.dto.validatedDto.ValidatedAuthCodeSendRequest;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signUp")
    public Response<MemberTokens> signUp(@RequestBody SignUpRequest request) {
        final ValidatedSignUpRequest validRequest = ValidatedSignUpRequest.from(request);
        final MemberTokens memberTokens = memberService.signUp(validRequest);
        return Response.success(memberTokens);
    }

    @PostMapping("/signIn")
    public Response<MemberTokens> signIn(@RequestBody SignInRequest request) {
        final MemberTokens memberTokens;
        try {
            final ValidatedSignInRequest validRequest = ValidatedSignInRequest.from(request);
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

    @PostMapping("/email/auth/send")
    public Response<Void> authCodeSend(@RequestBody AuthCodeSendRequest request) {
        final ValidatedAuthCodeSendRequest validatedRequest = ValidatedAuthCodeSendRequest.from(request);
        memberService.sendAuthCodeFromUniversityEmail(validatedRequest);
        return Response.success();
    }

    @PostMapping("/email/auth/verify")
    public Response<Void> SignUpAuthCodeValidate(@RequestBody SignUpAuthCodeValidateRequest request) {
        memberService.validateEmailAuthCode(request.getEmail(), request.getAuthCode());
        return Response.success();
    }

    @GetMapping("/token/reissue")
    public Response<MemberTokens> TokenReissue(HttpServletRequest request) {
        final MemberTokens memberTokens = memberService.reissueToken(request);
        return Response.success(memberTokens);
    }
}
