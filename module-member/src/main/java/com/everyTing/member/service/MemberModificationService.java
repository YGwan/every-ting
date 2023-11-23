package com.everyTing.member.service;

import com.everyTing.member.domain.Member;
import com.everyTing.member.domain.data.KakaoId;
import com.everyTing.member.domain.data.Password;
import com.everyTing.member.domain.data.ProfilePhoto;
import com.everyTing.member.domain.data.Username;
import com.everyTing.member.dto.validatedDto.ValidatedPasswordResetRequest;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
public class MemberModificationService {

    private final MemberQueryService memberQueryService;

    public MemberModificationService(MemberQueryService memberQueryService) {
        this.memberQueryService = memberQueryService;
    }

    @CachePut(value = "member", key = "#memberId")
    public Member modifyUsername(Long memberId, Username newUsername) {
        final Member member = memberQueryService.findMemberById(memberId);
        member.modifyUsername(newUsername);
        return member;
    }

    @CachePut(value = "member", key = "#memberId")
    public Member modifyKakaoId(Long memberId, KakaoId newValidateKakaoId) {
        final Member member = memberQueryService.findMemberById(memberId);
        member.modifyKakaoId(newValidateKakaoId);
        return member;
    }

    @CachePut(value = "member", key = "#memberId")
    public Member modifyPassword(Long memberId, Password newPassword) {
        final Member member = memberQueryService.findMemberById(memberId);
        member.modifyPassword(newPassword);
        return member;
    }

    @CachePut(value = "member", key = "#memberId")
    public Member modifyProfilePhoto(Long memberId, String url) {
        final var profilePhoto = ProfilePhoto.from(url);
        final Member member = memberQueryService.findMemberById(memberId);
        member.modifyProfilePhoto(profilePhoto);
        return member;
    }

    public Member resetPassword(ValidatedPasswordResetRequest validatedRequest) {
        final Member member = memberQueryService.findMemberByEmail(validatedRequest.getUniversityEmail());
        member.modifyPassword(validatedRequest.getPassword());
        return member;
    }
}
