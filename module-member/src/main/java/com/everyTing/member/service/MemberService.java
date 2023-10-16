package com.everyTing.member.service;

import com.everyTing.cache.EmailAuthCodeCache;
import com.everyTing.cache.EmailAuthCodeCacheRepository;
import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.core.token.data.MemberTokens;
import com.everyTing.core.token.service.TokenService;
import com.everyTing.member.domain.Member;
import com.everyTing.member.domain.data.KakaoId;
import com.everyTing.member.domain.data.UniversityEmail;
import com.everyTing.member.domain.data.Username;
import com.everyTing.member.dto.response.MemberInfoResponse;
import com.everyTing.member.dto.validatedDto.ValidatedAuthCodeSendRequest;
import com.everyTing.member.dto.validatedDto.ValidatedSignInRequest;
import com.everyTing.member.dto.validatedDto.ValidatedSignUpRequest;
import com.everyTing.member.repository.MemberRepository;
import com.everyTing.member.service.mail.MailService;
import com.everyTing.member.service.mail.form.SignUpForm;
import com.everyTing.member.utils.RandomCodeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;

import static com.everyTing.member.errorCode.MemberErrorCode.*;

@Transactional(readOnly = true)
@Service
public class MemberService {

    @Value("${mail.valid.time}")
    private Long mailValidTime;

    private final MemberRepository memberRepository;
    private final TokenService tokenService;
    private final MailService mailService;
    private final EmailAuthCodeCacheRepository emailAuthCodeCacheRepository;

    public MemberService(MemberRepository memberRepository, TokenService tokenService, MailService mailService, EmailAuthCodeCacheRepository emailAuthCodeCacheRepository) {
        this.memberRepository = memberRepository;
        this.tokenService = tokenService;
        this.mailService = mailService;
        this.emailAuthCodeCacheRepository = emailAuthCodeCacheRepository;
    }

    @Transactional
    public MemberTokens signUp(ValidatedSignUpRequest request) {
        validateSignUp(request);
        final Member newMember = memberRepository.save(Member.from(request));
        return tokenService.issue(newMember.getId());
    }

    @Transactional
    public MemberTokens signIn(ValidatedSignInRequest request) {
        Member member = memberRepository.findByUniversityEmailAndPassword(request.getEmail(), request.getPassword())
                .orElseThrow(() -> new TingApplicationException(MEMBER_010));
        return tokenService.issue(member.getId());
    }

    @Transactional
    public MemberTokens reissueToken(HttpServletRequest request) {
        return tokenService.reissue(request);
    }

    @Transactional
    public void sendAuthCodeFromUniversityEmail(ValidatedAuthCodeSendRequest request) {
        throwIfExistUniversityEmail(request.getUniversityEmail());

        final String username = request.getUsername().getValue();
        final String universityEmail = request.getUniversityEmail().getValue();
        final String emailAuthCode = RandomCodeUtils.generate();

        mailService.sendMail(universityEmail, new SignUpForm(username, emailAuthCode));
        emailAuthCodeCacheRepository.save(new EmailAuthCodeCache(universityEmail, emailAuthCode, LocalTime.now()));
    }

    public void validateEmailAuthCode(String email, String authCode) {
        final EmailAuthCodeCache emailAuthCodeCache = emailAuthCodeCacheRepository.findById(email).orElseThrow(() ->
                new TingApplicationException(MEMBER_013)
        );
        emailAuthCodeCache.checkValidTime(mailValidTime);
        emailAuthCodeCache.checkAuthCodeSame(authCode);
        emailAuthCodeCache.checkEmailSame(email);
    }

    private void validateSignUp(ValidatedSignUpRequest request) {
        throwIfExistUsername(request.getUsername());
        throwIfExistUniversityEmail(request.getUniversityEmail());
        throwIfExistKakaoId(request.getKakaoId());
    }

    public void throwIfExistUsername(Username username) {
        if (memberRepository.existsByUsername(username)) {
            throw new TingApplicationException(MEMBER_006);
        }
    }

    public void throwIfExistUniversityEmail(UniversityEmail email) {
        if (memberRepository.existsByUniversityEmail(email)) {
            throw new TingApplicationException(MEMBER_007);
        }
    }

    public void throwIfExistKakaoId(KakaoId kakaoId) {
        if (memberRepository.existsByKakaoId(kakaoId)) {
            throw new TingApplicationException(MEMBER_008);
        }
    }

    public MemberInfoResponse findMemberInfo(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() ->
                new TingApplicationException(MEMBER_014)
        );

        return MemberInfoResponse.from(member);
    }
}
