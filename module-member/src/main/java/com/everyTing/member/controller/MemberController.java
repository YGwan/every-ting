package com.everyTing.member.controller;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMember;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.core.token.data.MemberTokens;
import com.everyTing.member.dto.request.SignUpRequest;
import com.everyTing.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api/v1/member")
@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<MemberTokens> signUp(@RequestBody SignUpRequest request) {
        MemberTokens memberTokens = memberService.addMember(request);
        return Response.createSuccess(memberTokens);
    }

    @GetMapping("/reissue/token")
    public Response<MemberTokens> reissueToken(HttpServletRequest request) {
        MemberTokens memberTokens = memberService.reissueToken(request);
        return Response.success(memberTokens);

    }

    @GetMapping("/test")
    public Response<Long> test(@LoginMember LoginMemberInfo memberInfo) {
        final Long memberId = memberInfo.getId();
        return Response.success(memberId);
    }
}
