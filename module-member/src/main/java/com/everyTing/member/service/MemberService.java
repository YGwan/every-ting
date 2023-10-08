package com.everyTing.member.service;

import com.everyTing.core.token.service.TokenService;
import com.everyTing.core.token.data.MemberTokens;
import com.everyTing.member.domain.Member;
import com.everyTing.member.domain.data.KakaoId;
import com.everyTing.member.domain.data.Username;
import com.everyTing.member.dto.validatedDto.ValidatedSignUpRequest;
import com.everyTing.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final TokenService tokenService;

    public MemberService(MemberRepository memberRepository, TokenService tokenService) {
        this.memberRepository = memberRepository;
        this.tokenService = tokenService;
    }

    public boolean existsMemberByUsername(Username username) {
        return memberRepository.existsByUsername(username);
    }

    public boolean existsMemberByKakaoId(KakaoId kakaoId) {
        return memberRepository.existsByKakaoId(kakaoId);
    }

    public MemberTokens addMember(ValidatedSignUpRequest request) {
        Member newMember = memberRepository.save(Member.from(request));
        return tokenService.issue(newMember.getId());
    }

    public MemberTokens reissueToken(HttpServletRequest request) {
        return tokenService.reissue(request);
    }
}
