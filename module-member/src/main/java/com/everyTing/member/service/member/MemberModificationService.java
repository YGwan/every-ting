package com.everyTing.member.service.member;

import com.everyTing.member.domain.Member;
import com.everyTing.member.domain.data.KakaoId;
import com.everyTing.member.domain.data.Password;
import com.everyTing.member.domain.data.ProfilePhoto;
import com.everyTing.member.domain.data.Username;
import com.everyTing.member.dto.request.PasswordModifyRequest;
import com.everyTing.member.dto.request.PasswordResetRequest;
import com.everyTing.member.repository.MemberRepository;
import com.everyTing.member.service.EncryptService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
public class MemberModificationService {

    private final MemberQueryService memberQueryService;
    private final MemberRepository memberRepository;
    private final EncryptService encryptService;

    public MemberModificationService(MemberQueryService memberQueryService, MemberRepository memberRepository, EncryptService encryptService) {
        this.memberQueryService = memberQueryService;
        this.memberRepository = memberRepository;
        this.encryptService = encryptService;
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
        final Password encryptedPassword = getPassword(enterPassword);
        member.modifyPassword(encryptedPassword);
        memberRepository.save(member);
    }

    private Password getPassword(String password) {
        final var salt = encryptService.issueSalt();
        return encryptService.encryptedPassword(password, salt);
    }
}
