package com.everyTing.member.service;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.member.domain.Member;
import com.everyTing.member.domain.data.UniversityEmail;
import com.everyTing.member.dto.response.MemberInfoResponse;
import com.everyTing.member.dto.validatedDto.ValidatedSignInRequest;
import com.everyTing.member.dto.validatedDto.ValidatedSignUpRequest;
import com.everyTing.member.repository.MemberRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.everyTing.member.errorCode.MemberErrorCode.MEMBER_010;
import static com.everyTing.member.errorCode.MemberErrorCode.MEMBER_014;

@Service
public class MemberQueryService {

    private final MemberRepository memberRepository;
    private final MemberDataDeleteService memberDataDeleteService;

    public MemberQueryService(MemberRepository memberRepository, MemberDataDeleteService memberDataDeleteService) {
        this.memberRepository = memberRepository;
        this.memberDataDeleteService = memberDataDeleteService;
    }

    public Long signUp(ValidatedSignUpRequest request) {
        final Member newMember = memberRepository.save(Member.from(request));
        return newMember.getId();
    }

    public Long signIn(ValidatedSignInRequest request) {
        Member member = memberRepository.findByUniversityEmailAndPassword(request.getEmail(), request.getPassword())
                .orElseThrow(() -> new TingApplicationException(MEMBER_010));
        return member.getId();
    }

    public List<MemberInfoResponse> findMembersInfo(List<Long> memberIds) {
        return memberRepository.findByIdIn(memberIds)
                .stream()
                .map(MemberInfoResponse::from)
                .collect(Collectors.toUnmodifiableList());
    }

    @Cacheable(value = "member", key = "#memberId")
    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() ->
                new TingApplicationException(MEMBER_014)
        );
    }

    @CacheEvict(value = "member", key = "#memberId")
    public void removeMember(Long memberId) {
        memberDataDeleteService.deleteMemberData(memberId);
        memberRepository.deleteById(memberId);
    }

    public Member findMemberByEmail(UniversityEmail email) {
        return memberRepository.findByUniversityEmail(email).orElseThrow(() ->
                new TingApplicationException(MEMBER_014)
        );
    }
}
