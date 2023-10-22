package com.everyTing.member.controller;

import com.everyTing.core.dto.Response;
import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.core.resolver.LoginMember;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.core.token.data.MemberTokens;
import com.everyTing.member.domain.data.KakaoId;
import com.everyTing.member.domain.data.Password;
import com.everyTing.member.domain.data.UniversityEmail;
import com.everyTing.member.domain.data.Username;
import com.everyTing.member.dto.request.*;
import com.everyTing.member.dto.response.MemberInfoResponse;
import com.everyTing.member.dto.validatedDto.ValidatedAuthCodeSendForSignUpRequest;
import com.everyTing.member.dto.validatedDto.ValidatedPasswordResetRequest;
import com.everyTing.member.dto.validatedDto.ValidatedSignInRequest;
import com.everyTing.member.dto.validatedDto.ValidatedSignUpRequest;
import com.everyTing.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

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

    @GetMapping("/info")
    public Response<List<MemberInfoResponse>> MembersInfoDetails(@RequestBody MembersInfoDetailsRequest request) {
        final var myInfoResponse = memberService.findMembersInfo(request);
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

    @GetMapping("/password/check")
    public Response<Void> passwordCheck(@LoginMember LoginMemberInfo memberInfo,
                                        @RequestBody PasswordCheckRequest request) {
        final Password password = Password.from(request.getPassword());
        memberService.throwIfNotValidatePassword(memberInfo.getId(), password);
        return Response.success();
    }

    @GetMapping("/token/check")
    public Response<Void> tokenCheck(HttpServletRequest request) {
        memberService.throwIfNotValidateToken(request);
        return Response.success();
    }

    @PostMapping("/signUp/email/auth/send")
    public Response<Void> authCodeSendForSignUp(@RequestBody AuthCodeSendForSignUpRequest request) {
        final var validatedRequest = ValidatedAuthCodeSendForSignUpRequest.from(request);
        memberService.sendAuthCodeForSignUp(validatedRequest);
        return Response.success();
    }

    @PostMapping("/password/reset/email/auth/send")
    public Response<Void> authCodeSendForResetPassword(@RequestBody AuthCodeSendForResetPasswordRequest request) {
        final var validatedUniversityEmail = UniversityEmail.from(request.getUniversityEmail());
        memberService.sendAuthCodeForResetPassword(validatedUniversityEmail);
        return Response.success();
    }

    @PostMapping("/email/auth/verify")
    public Response<Void> emailAuthCodeValidate(@RequestBody EmailAuthCodeValidateRequest request) {
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

    @PutMapping("/kakaoId/modify")
    public Response<Void> kakaoIdModify(@LoginMember LoginMemberInfo memberInfo,
                                        @RequestParam String kakaoId) {
        final var newValidateKakaoId = KakaoId.from(kakaoId);
        memberService.modifyKakaoId(memberInfo.getId(), newValidateKakaoId);
        return Response.success();
    }

    @PutMapping("/password/modify")
    public Response<Void> passwordModify(@LoginMember LoginMemberInfo memberInfo,
                                         @RequestBody PasswordModifyRequest request) {
        final Password newPassword = Password.from(request.getPassword());
        memberService.modifyPassword(memberInfo.getId(), newPassword);
        return Response.success();
    }

    @PutMapping("/password/reset")
    public Response<Void> passwordReset(@RequestBody PasswordResetRequest request) {
        final var validatedRequest = ValidatedPasswordResetRequest.from(request);
        memberService.resetPassword(validatedRequest);
        return Response.success();
    }

    @GetMapping("/token/reissue")
    public Response<MemberTokens> TokenReissue(HttpServletRequest request) {
        final var memberTokens = memberService.reissueToken(request);
        return Response.success(memberTokens);
    }
}
