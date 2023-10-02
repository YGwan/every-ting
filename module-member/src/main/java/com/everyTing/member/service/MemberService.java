package com.everyTing.member.service;

import com.everyTing.core.token.service.TokenService;
import com.everyTing.core.token.type.MemberTokens;
import com.everyTing.member.domain.Member;
import com.everyTing.member.dto.request.SignUpRequest;
import com.everyTing.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final TokenService tokenService;

    public MemberService(MemberRepository memberRepository, TokenService tokenService) {
        this.memberRepository = memberRepository;
        this.tokenService = tokenService;
    }

    public MemberTokens addMember(SignUpRequest request) {
        Member newMember = memberRepository.save(Member.from(request));
        return tokenService.issue(newMember.getId());
    }
}
