package com.everyTing.member.service;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.member.domain.Member;
import com.everyTing.member.domain.data.UniversityEmail;
import com.everyTing.member.repository.MemberRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static com.everyTing.member.errorCode.MemberErrorCode.MEMBER_014;

@Service
public class MemberInquiryService {

    private final MemberRepository memberRepository;

    public MemberInquiryService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Cacheable(cacheNames = "memberCache", key = "#memberId")
    public Member getMemberById(Long memberId) {
        return findMemberById(memberId);
    }

    @Cacheable(cacheNames = "memberCache", key = "#memberId")
    public Member getMemberByEmail(UniversityEmail email) {
        return findMemberByEmail(email);
    }

    private Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() ->
                new TingApplicationException(MEMBER_014)
        );
    }

    private Member findMemberByEmail(UniversityEmail email) {
        return memberRepository.findByUniversityEmail(email).orElseThrow(() ->
                new TingApplicationException(MEMBER_014)
        );
    }
}
