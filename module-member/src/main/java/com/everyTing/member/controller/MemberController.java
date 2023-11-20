package com.everyTing.member.controller;

import com.everyTing.core.dto.Response;
import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.core.resolver.LoginMember;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.core.token.service.TokenService;
import com.everyTing.member.domain.data.KakaoId;
import com.everyTing.member.domain.data.Username;
import com.everyTing.member.dto.request.PasswordCheckRequest;
import com.everyTing.member.dto.request.PasswordModifyRequest;
import com.everyTing.member.dto.request.SignInRequest;
import com.everyTing.member.dto.request.SignUpRequest;
import com.everyTing.member.dto.response.MemberInfoResponse;
import com.everyTing.member.dto.response.MemberTokensResponse;
import com.everyTing.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.everyTing.member.errorCode.MemberErrorCode.MEMBER_010;

@RequestMapping("/api/v1/members")
@RestController
public class MemberController {

    private final MemberService memberService;
    private final TokenService tokenService;

    public MemberController(MemberService memberService, TokenService tokenService) {
        this.memberService = memberService;
        this.tokenService = tokenService;
    }

    @GetMapping("/my/info")
    public Response<MemberInfoResponse> myInfoDetails(@LoginMember LoginMemberInfo memberInfo) {
        final Long memberId = memberInfo.getId();
        final var memberInfoResponse = memberService.findMemberInfo(memberId);
        return Response.success(memberInfoResponse);
    }

    @GetMapping("/{memberId}/info")
    public Response<MemberInfoResponse> memberInfoDetails(@PathVariable Long memberId) {
        final var myInfoResponse = memberService.findMemberInfo(memberId);
        return Response.success(myInfoResponse);
    }

    @GetMapping("/info")
    public Response<List<MemberInfoResponse>> membersInfoDetails(@RequestParam List<Long> memberIds) {
        final var myInfoResponse = memberService.findMembersInfo(memberIds);
        return Response.success(myInfoResponse);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signUp")
    public Response<MemberTokensResponse> signUp(@RequestBody SignUpRequest request) {
        final var memberId = memberService.signUp(request);
        return getMemberTokensResponse(memberId);
    }

    @PostMapping("/signIn")
    public Response<MemberTokensResponse> signIn(@RequestBody SignInRequest request) {
        try {
            final var memberId = memberService.signIn(request);
            return getMemberTokensResponse(memberId);
        } catch (TingApplicationException e) {
            throw new TingApplicationException(MEMBER_010);
        }
    }

    private Response<MemberTokensResponse> getMemberTokensResponse(Long memberId) {
        final var memberTokens = tokenService.issue(memberId);
        final var memberTokensResponse = new MemberTokensResponse(memberId, memberTokens);
        return Response.success(memberTokensResponse);
    }

    @GetMapping("/username/check")
    public Response<Void> usernameCheck(@RequestParam String username) {
        memberService.throwIfAlreadyExistedUsername(Username.from(username));
        return Response.success();
    }

    @GetMapping("/kakaoId/check")
    public Response<Void> kakaoIdCheck(@RequestParam String kakaoId) {
        memberService.throwIfAlreadyExistedKakaoId(KakaoId.from(kakaoId));
        return Response.success();
    }

    @GetMapping("/password/check")
    public Response<Void> passwordCheck(@LoginMember LoginMemberInfo memberInfo,
                                        @RequestBody PasswordCheckRequest request) {
        memberService.throwIfNotValidatePassword(memberInfo.getId(), request);
        return Response.success();
    }

    @PutMapping("/username/modify")
    public Response<Long> usernameModify(@LoginMember LoginMemberInfo memberInfo,
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
//        final Password newPassword = Password.from(request.getPassword());
//        memberService.modifyPassword(memberInfo.getId(), newPassword);
        return Response.success();
    }

//    @PutMapping("/password/reset")
//    public Response<Void> passwordReset(@RequestBody PasswordResetRequest request) {
//        final var validatedRequest = ValidatedPasswordResetRequest.from(request);
//        memberService.resetPassword(validatedRequest);
//        return Response.success();
//    }

    @DeleteMapping
    public Response<Void> memberRemove(@LoginMember LoginMemberInfo memberInfo) {
        memberService.removeMember(memberInfo.getId());
        return Response.success();
    }
}
