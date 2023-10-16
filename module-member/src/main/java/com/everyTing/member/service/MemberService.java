package com.everyTing.member.service;

import com.everyTing.cache.EmailAuthCodeCache;
import com.everyTing.cache.EmailAuthCodeCacheRepository;
import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.core.exception.TingServerException;
import com.everyTing.core.token.data.MemberTokens;
import com.everyTing.core.token.service.TokenService;
import com.everyTing.member.domain.Member;
import com.everyTing.member.domain.data.KakaoId;
import com.everyTing.member.domain.data.Username;
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
        final Member newMember = memberRepository.save(Member.from(request));
        return tokenService.issue(newMember.getId());
    }

    @Transactional
    public MemberTokens signIn(ValidatedSignInRequest request) {
        Member member = memberRepository.findByUniversityEmailAndPassword(request.getEmail(), request.getPassword())
                .orElseThrow(() -> new TingApplicationException(MEMBER_009));
        return tokenService.issue(member.getId());
    }

    @Transactional
    public MemberTokens reissueToken(HttpServletRequest request) {
        return tokenService.reissue(request);
    }

    @Transactional
    public void sendAuthCodeFromUniversityEmail(String username, String universityEmail) {
        final String emailAuthCode = RandomCodeUtils.generate();
        mailService.sendMail(universityEmail, new SignUpForm(username, emailAuthCode));
        emailAuthCodeCacheRepository.save(new EmailAuthCodeCache(universityEmail, emailAuthCode, LocalTime.now()));
    }

    public void validateEmailAuthCode(String email, String authCode) {
        final EmailAuthCodeCache emailAuthCodeCache = emailAuthCodeCacheRepository.findById(email).orElseThrow(() ->
                new TingApplicationException(MEMBER_012)
        );
        emailAuthCodeCache.checkValidTime(mailValidTime);
        emailAuthCodeCache.checkAuthCodeSame(authCode);
        emailAuthCodeCache.checkEmailSame(email);
    }

    public void throwIfExistUsername(Username username) {
        if (memberRepository.existsByUsername(username)) {
            throw new TingServerException(MEMBER_006);
        }
    }

    public void throwIfExistKakaoId(KakaoId kakaoId) {
        if (memberRepository.existsByKakaoId(kakaoId)) {
            throw new TingServerException(MEMBER_007);
        }
    }
}
