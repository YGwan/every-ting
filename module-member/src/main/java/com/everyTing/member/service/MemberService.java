package com.everyTing.member.service;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.member.domain.Member;
import com.everyTing.member.domain.data.KakaoId;
import com.everyTing.member.domain.data.Password;
import com.everyTing.member.domain.data.ProfilePhoto;
import com.everyTing.member.domain.data.Username;
import com.everyTing.member.dto.response.MemberInfoResponse;
import com.everyTing.member.dto.validatedDto.ValidatedPasswordResetRequest;
import com.everyTing.member.dto.validatedDto.ValidatedSignInRequest;
import com.everyTing.member.dto.validatedDto.ValidatedSignUpRequest;
import com.everyTing.member.repository.MemberRepository;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.everyTing.member.errorCode.MemberErrorCode.MEMBER_010;
import static com.everyTing.member.errorCode.MemberErrorCode.MEMBER_016;

@Transactional
@Service
public class MemberService extends MemberServiceValidator {

    private final MemberRepository memberRepository;
    private final MemberDataDeleteService memberDataDeleteService;
    private final MemberInquiryService memberInquiryService;

    public MemberService(MemberRepository memberRepository, MemberDataDeleteService memberDataDeleteService, MemberInquiryService memberInquiryService) {
        super(memberRepository);
        this.memberRepository = memberRepository;
        this.memberDataDeleteService = memberDataDeleteService;
        this.memberInquiryService = memberInquiryService;
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
        memberDataDeleteService.deleteMemberData(memberId);
        memberRepository.deleteById(memberId);
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
        final Member member = memberInquiryService.getMemberById(memberId);
        return MemberInfoResponse.from(member);
    }

    @CachePut(cacheNames = "MemberCache", key = "#memberId", cacheManager = "memberCacheManager")
    public void modifyUsername(Long memberId, Username newUsername) {
        throwIfAlreadyExisted(newUsername);
        final Member member = memberInquiryService.getMemberById(memberId);
        member.modifyUsername(newUsername);
    }

    @CachePut(cacheNames = "MemberCache", key = "#memberId", cacheManager = "memberCacheManager")
    public void modifyKakaoId(Long memberId, KakaoId newValidateKakaoId) {
        throwIfAlreadyExisted(newValidateKakaoId);
        final Member member = memberInquiryService.getMemberById(memberId);
        member.modifyKakaoId(newValidateKakaoId);
    }

    @CachePut(cacheNames = "MemberCache", key = "#memberId", cacheManager = "memberCacheManager")
    public void modifyPassword(Long memberId, Password newPassword) {
        final Member member = memberInquiryService.getMemberById(memberId);
        member.modifyPassword(newPassword);
    }

    @CachePut(cacheNames = "MemberCache", key = "#memberId", cacheManager = "memberCacheManager")
    public void modifyProfilePhoto(Long memberId, String url) {
        final var profilePhoto = ProfilePhoto.from(url);
        final Member member = memberInquiryService.getMemberById(memberId);
        member.modifyProfilePhoto(profilePhoto);
    }

    @CachePut(cacheNames = "MemberCache", key = "#memberId", cacheManager = "memberCacheManager")
    public void resetPassword(ValidatedPasswordResetRequest validatedRequest) {
        final Member member = memberInquiryService.getMemberByEmail(validatedRequest.getUniversityEmail());
        member.modifyPassword(validatedRequest.getPassword());
    }

    private void validateSignUp(ValidatedSignUpRequest request) {
        throwIfAlreadyExisted(request.getUsername());
        throwIfAlreadyExisted(request.getUniversityEmail());
        throwIfAlreadyExisted(request.getKakaoId());
    }

    public void throwIfNotValidatePassword(Long memberId, Password password) {
        final Member member = memberInquiryService.getMemberById(memberId);
        if (!password.equals(member.getPassword())) {
            throw new TingApplicationException(MEMBER_016);
        }
    }
}
