package com.everyTing.member.service;

import com.everyTing.member.domain.Member;
import com.everyTing.member.domain.data.KakaoId;
import com.everyTing.member.domain.data.Password;
import com.everyTing.member.domain.data.ProfilePhoto;
import com.everyTing.member.domain.data.Username;
import com.everyTing.member.dto.request.PasswordModifyRequest;
import com.everyTing.member.dto.request.PasswordResetRequest;
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
        return member;
    }

    public Member modifyPassword(Long memberId, PasswordModifyRequest request) {
        final Member member = memberQueryService.findMemberById(memberId);
        modifyPassword(member, request.getPassword());
        return member;
    }

    public Member resetPassword(PasswordResetRequest request) {
        final var universityEmail = request.universityEmailEntity();
        final Member member = memberQueryService.findMemberByEmail(universityEmail);
        modifyPassword(member, request.getPassword());
        return member;
    }

    private void modifyPassword(Member member, String enterPassword) {
        final Password encryptedPassword = Password.encryptedPassword(enterPassword);
        member.modifyPassword(encryptedPassword);
        memberRepository.save(member);
    }
}
