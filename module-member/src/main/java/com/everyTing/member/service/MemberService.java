package com.everyTing.member.service;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.member.domain.Member;
import com.everyTing.member.domain.data.KakaoId;
import com.everyTing.member.domain.data.Password;
import com.everyTing.member.domain.data.UniversityEmail;
import com.everyTing.member.domain.data.Username;
import com.everyTing.member.dto.response.MemberInfoResponse;
import com.everyTing.member.dto.validatedDto.ValidatedPasswordResetRequest;
import com.everyTing.member.dto.validatedDto.ValidatedSignInRequest;
import com.everyTing.member.dto.validatedDto.ValidatedSignUpRequest;
import com.everyTing.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.everyTing.member.errorCode.MemberErrorCode.*;

@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryService memberQueryService;
    private final MemberModificationService memberModificationService;

    public MemberService(MemberRepository memberRepository, MemberQueryService memberQueryService, MemberModificationService memberModificationService) {
        this.memberRepository = memberRepository;
        this.memberQueryService = memberQueryService;
        this.memberModificationService = memberModificationService;
    }

    @Transactional(readOnly = true)
    public Long signUp(ValidatedSignUpRequest request) {
        throwIfAlreadyExistedUsername(request.getUsername());
        throwIfAlreadyExistedEmail(request.getUniversityEmail());
        throwIfAlreadyExistedKakaoId(request.getKakaoId());

        return memberQueryService.signUp(request);
    }

    @Transactional(readOnly = true)
    public Long signIn(ValidatedSignInRequest request) {
        return memberQueryService.signIn(request);
    }

    @Transactional(readOnly = true)
    public List<MemberInfoResponse> findMembersInfo(List<Long> memberIds) {
        return memberQueryService.findMembersInfo(memberIds);
    }

    @Transactional(readOnly = true)
    public MemberInfoResponse findMemberInfo(Long memberId) {
        final Member member = memberQueryService.findMemberById(memberId);
        return MemberInfoResponse.from(member);
    }

    public void removeMember(Long memberId) {
        memberQueryService.removeMember(memberId);
    }

    public Long modifyUsername(Long memberId, Username newUsername) {
        throwIfAlreadyExistedUsername(newUsername);
        final var member = memberModificationService.modifyUsername(memberId, newUsername);
        return member.getId();
    }

    public Long modifyKakaoId(Long memberId, KakaoId newValidateKakaoId) {
        throwIfAlreadyExistedKakaoId(newValidateKakaoId);
        final var member = memberModificationService.modifyKakaoId(memberId, newValidateKakaoId);
        return member.getId();
    }

    public Long modifyPassword(Long memberId, Password newPassword) {
        final var member = memberModificationService.modifyPassword(memberId, newPassword);
        return member.getId();
    }

    public Long modifyProfilePhoto(Long memberId, String url) {
        final var member = memberModificationService.modifyProfilePhoto(memberId, url);
        return member.getId();
    }

    public Long resetPassword(ValidatedPasswordResetRequest validatedRequest) {
        final var member = memberModificationService.resetPassword(validatedRequest);
        return member.getId();
    }

    public void throwIfNotExisted(UniversityEmail email) {
        if (!memberRepository.existsByUniversityEmail(email)) {
            throw new TingApplicationException(MEMBER_015);
        }
    }

    public void throwIfAlreadyExistedUsername(Username username) {
        if (memberRepository.existsByUsername(username)) {
            throw new TingApplicationException(MEMBER_006);
        }
    }

    public void throwIfAlreadyExistedEmail(UniversityEmail email) {
        if (memberRepository.existsByUniversityEmail(email)) {
            throw new TingApplicationException(MEMBER_007);
        }
    }

    public void throwIfAlreadyExistedKakaoId(KakaoId kakaoId) {
        if (memberRepository.existsByKakaoId(kakaoId)) {
            throw new TingApplicationException(MEMBER_008);
        }
    }

    public void throwIfNotValidatePassword(Long memberId, Password password) {
        final Member member = memberQueryService.findMemberById(memberId);

        if (!password.equals(member.getPassword())) {
            throw new TingApplicationException(MEMBER_016);
        }
    }
}
