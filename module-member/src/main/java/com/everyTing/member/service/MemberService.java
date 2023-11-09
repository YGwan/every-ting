package com.everyTing.member.service;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.member.domain.Member;
import com.everyTing.member.domain.data.*;
import com.everyTing.member.dto.response.MemberInfoResponse;
import com.everyTing.member.dto.validatedDto.ValidatedPasswordResetRequest;
import com.everyTing.member.dto.validatedDto.ValidatedSignInRequest;
import com.everyTing.member.dto.validatedDto.ValidatedSignUpRequest;
import com.everyTing.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.everyTing.member.errorCode.MemberErrorCode.*;

@Transactional
@Service
public class MemberService extends MemberServiceValidator {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        super(memberRepository);
        this.memberRepository = memberRepository;
    }

    public Long signUp(ValidatedSignUpRequest request) {
        validateSignUp(request);
        final Member newMember = memberRepository.save(Member.from(request));
        return newMember.getId();
    }

    @Transactional(readOnly = true)
    public Long signIn(ValidatedSignInRequest request) {
        Member member = memberRepository.findByUniversityEmailAndPassword(request.getEmail(), request.getPassword())
                .orElseThrow(() -> new TingApplicationException(MEMBER_010));
        return member.getId();
    }

    public void removeMember(Long memberId) {
        final Member member = getMemberById(memberId);
        memberRepository.delete(member);
    }

    @Transactional(readOnly = true)
    public List<MemberInfoResponse> findMembersInfo(List<Long> memberIds) {
        return memberRepository.findByIdIn(memberIds)
                .stream()
                .map(MemberInfoResponse::from)
                .collect(Collectors.toUnmodifiableList());
    }

    @Transactional(readOnly = true)
    public MemberInfoResponse findMemberInfo(Long memberId) {
        final Member member = getMemberById(memberId);
        return MemberInfoResponse.from(member);
    }

    public void modifyUsername(Long memberId, Username newUsername) {
        throwIfAlreadyExisted(newUsername);
        final Member member = getMemberById(memberId);
        member.modifyUsername(newUsername);
    }

    public void modifyKakaoId(Long memberId, KakaoId newValidateKakaoId) {
        throwIfAlreadyExisted(newValidateKakaoId);
        final Member member = getMemberById(memberId);
        member.modifyKakaoId(newValidateKakaoId);
    }

    public void modifyPassword(Long memberId, Password newPassword) {
        final Member member = getMemberById(memberId);
        member.modifyPassword(newPassword);
    }

    public void modifyProfilePhoto(Long memberId, String url) {
        final var profilePhoto = ProfilePhoto.from(url);
        final Member member = getMemberById(memberId);
        member.modifyProfilePhoto(profilePhoto);
    }

    public void resetPassword(ValidatedPasswordResetRequest validatedRequest) {
        final Member member = getMemberByEmail(validatedRequest.getUniversityEmail());
        member.modifyPassword(validatedRequest.getPassword());
    }

    private void validateSignUp(ValidatedSignUpRequest request) {
        throwIfAlreadyExisted(request.getUsername());
        throwIfAlreadyExisted(request.getUniversityEmail());
        throwIfAlreadyExisted(request.getKakaoId());
    }

    public void throwIfNotValidatePassword(Long memberId, Password password) {
        final Member member = getMemberById(memberId);
        if (!password.equals(member.getPassword())) {
            throw new TingApplicationException(MEMBER_016);
        }
    }

    private Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() ->
                new TingApplicationException(MEMBER_014)
        );
    }

    private Member getMemberByEmail(UniversityEmail email) {
        return memberRepository.findByUniversityEmail(email).orElseThrow(() ->
                new TingApplicationException(MEMBER_014)
        );
    }
}
