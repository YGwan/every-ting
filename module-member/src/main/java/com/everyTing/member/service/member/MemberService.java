package com.everyTing.member.service.member;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.member.domain.Member;
import com.everyTing.member.domain.data.KakaoId;
import com.everyTing.member.domain.data.UniversityEmail;
import com.everyTing.member.domain.data.Username;
import com.everyTing.member.dto.request.*;
import com.everyTing.member.dto.response.MemberInfoResponse;
import com.everyTing.member.repository.MemberRepository;
import com.everyTing.member.service.encrypt.EncryptService;
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
    private final EncryptService encryptService;

    public MemberService(MemberRepository memberRepository, MemberQueryService memberQueryService, MemberModificationService memberModificationService, EncryptService encryptService) {
        this.memberRepository = memberRepository;
        this.memberQueryService = memberQueryService;
        this.memberModificationService = memberModificationService;
        this.encryptService = encryptService;
    }

    @Transactional(readOnly = true)
    public Long signUp(SignUpRequest request) {
        throwIfAlreadyExistedUsername(request.usernameEntity());
        throwIfAlreadyExistedEmail(request.universityEmailEntity());
        throwIfAlreadyExistedKakaoId(request.kakaoIdEntity());

        return memberQueryService.signUp(request);
    }

    @Transactional(readOnly = true)
    public Long signIn(SignInRequest request) {
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

    public Long modifyPassword(Long memberId, PasswordModifyRequest request) {
        final var member = memberModificationService.modifyPassword(memberId, request);
        return member.getId();
    }

    public Long modifyProfilePhoto(Long memberId, String url) {
        final var member = memberModificationService.modifyProfilePhoto(memberId, url);
        return member.getId();
    }

    public Long resetPassword(PasswordResetRequest request) {
        final var member = memberModificationService.resetPassword(request);
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

    public void throwIfNotValidatePassword(Long memberId, PasswordCheckRequest request) {
        final Member member = memberRepository.findById(memberId).orElseThrow(() ->
                new TingApplicationException(MEMBER_014)
        );

        final var enterPassword = encryptService.encryptedPassword(request.getPassword(), member.getSalt());

        if (!member.isSamePassword(enterPassword)) {
            throw new TingApplicationException(MEMBER_016);
        }
    }
}
