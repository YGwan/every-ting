package com.everyTing.member.service;

import com.everyTing.member.domain.Member;
import com.everyTing.member.dto.request.SignUpRequest;
import com.everyTing.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void addMember(SignUpRequest request) {
        memberRepository.save(Member.from(request));
    }
}
