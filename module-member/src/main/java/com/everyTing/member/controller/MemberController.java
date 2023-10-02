package com.everyTing.member.controller;

import com.everyTing.core.dto.Response;
import com.everyTing.core.token.type.MemberTokens;
import com.everyTing.member.dto.request.SignUpRequest;
import com.everyTing.member.service.MemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/member")
@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/signUp")
    public Response<MemberTokens> signUp(@RequestBody SignUpRequest request) {
        MemberTokens memberTokens = memberService.addMember(request);
        return Response.success(memberTokens);
    }
}
