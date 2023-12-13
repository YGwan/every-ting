package com.everyTing.member.service.member;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.member.domain.Member;
import com.everyTing.member.domain.data.Password;
import com.everyTing.member.domain.data.UniversityEmail;
import com.everyTing.member.dto.request.SignInRequest;
import com.everyTing.member.dto.request.SignUpRequest;
import com.everyTing.member.dto.response.MemberInfoResponse;
import com.everyTing.member.repository.MemberRepository;
import com.everyTing.member.service.encrypt.EncryptService;
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
    private final EncryptService encryptService;

    public MemberQueryService(MemberRepository memberRepository, MemberDataDeleteService memberDataDeleteService, EncryptService encryptService) {
        this.memberRepository = memberRepository;
        this.memberDataDeleteService = memberDataDeleteService;
        this.encryptService = encryptService;
    }

    public Long signUp(SignUpRequest request) {
        final var salt = encryptService.issueSalt();
        final var password = password(request.getPassword(), salt);

        final var memberEntity = request.withPassword(password);
        final var newMember = memberRepository.save(memberEntity);
        return newMember.getId();
    }

    public Long signIn(SignInRequest request) {
        final var universityEntity = request.universityEmailEntity();
        final Member member = memberRepository.findByUniversityEmail(universityEntity)
                .orElseThrow(() -> new TingApplicationException(MEMBER_010));

        final var memberSalt = member.getSalt();
        final var password = password(request.getPassword(), memberSalt);

        if (!member.isSamePassword(password)) {
            throw new TingApplicationException(MEMBER_010);
        }

        return member.getId();
    }

    public Password password(String password, String salt) {
        Password.validate(password);
        return encryptService.encryptedPassword(password, salt);
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
