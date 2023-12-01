package com.everyTing.member.service.member;

import com.everyTing.member.domain.Member;
import com.everyTing.member.domain.data.*;
import com.everyTing.member.repository.MemberRepository;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
public class MemberModificationService {

    private final MemberQueryService memberQueryService;
    private final MemberRepository memberRepository;

    public MemberModificationService(MemberQueryService memberQueryService, MemberRepository memberRepository) {
        this.memberQueryService = memberQueryService;
        this.memberRepository = memberRepository;
    }

    @CachePut(value = "member", key = "#memberId")
    public Member modifyUsername(Long memberId, Username newUsername) {
        final Member member = memberQueryService.findMemberById(memberId);
        member.modifyUsername(newUsername);
        memberRepository.save(member);
        return member;
    }

    @CachePut(value = "member", key = "#memberId")
    public Member modifyKakaoId(Long memberId, KakaoId newValidateKakaoId) {
        final Member member = memberQueryService.findMemberById(memberId);
        member.modifyKakaoId(newValidateKakaoId);
        memberRepository.save(member);
        return member;
    }

    @CachePut(value = "member", key = "#memberId")
    public Member modifyProfilePhoto(Long memberId, String url) {
        final var profilePhoto = ProfilePhoto.from(url);
        final Member member = memberQueryService.findMemberById(memberId);
        member.modifyProfilePhoto(profilePhoto);
        memberRepository.save(member);
        return member;
    }

    public Member modifyPassword(Long memberId, Password newPassword) {
        final Member member = memberQueryService.findMemberById(memberId);
        modifyPassword(member, newPassword);
        return member;
    }

    public Member resetPassword(UniversityEmail universityEmail, Password newPassword) {
        final Member member = memberQueryService.findMemberByEmail(universityEmail);
        modifyPassword(member, newPassword);
        return member;
    }

    private void modifyPassword(Member member, Password password) {
        member.modifyPassword(password);
        memberRepository.save(member);
    }
}
