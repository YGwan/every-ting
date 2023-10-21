package com.everyTing.member.service;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.core.token.data.MemberTokens;
import com.everyTing.core.token.service.TokenService;
import com.everyTing.member.cache.EmailAuthCodeCache;
import com.everyTing.member.cache.EmailAuthCodeCacheRepository;
import com.everyTing.member.domain.Member;
import com.everyTing.member.domain.data.KakaoId;
import com.everyTing.member.domain.data.Password;
import com.everyTing.member.domain.data.UniversityEmail;
import com.everyTing.member.domain.data.Username;
import com.everyTing.member.dto.response.MemberInfoResponse;
import com.everyTing.member.dto.validatedDto.*;
import com.everyTing.member.repository.MemberRepository;
import com.everyTing.member.service.mail.MailService;
import com.everyTing.member.service.mail.form.ResetPasswordForm;
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
    public void sendAuthCodeForSignUp(ValidatedAuthCodeSendForSignUpRequest request) {
        throwIfExistUniversityEmail(request.getUniversityEmail());

        final String username = request.getUsernameValue();
        final String universityEmail = request.getUniversityEmailValue();
        final String emailAuthCode = RandomCodeUtils.generate();

        mailService.sendMail(universityEmail, new SignUpForm(username, emailAuthCode));
        emailAuthCodeCacheRepository.save(new EmailAuthCodeCache(universityEmail, emailAuthCode, LocalTime.now()));
    }

    @Transactional
    public void sendAuthCodeForResetPassword(UniversityEmail email) {
        throwIfNotExistEmail(email);

        final String universityEmail = email.getValue();
        final String emailAuthCode = RandomCodeUtils.generate();

        mailService.sendMail(universityEmail, new ResetPasswordForm(emailAuthCode));
        emailAuthCodeCacheRepository.save(new EmailAuthCodeCache(universityEmail, emailAuthCode, LocalTime.now()));
    }

    @Transactional
    public void modifyUsername(Long memberId, Username newUsername) {
        throwIfExistUsername(newUsername);
        final Member member = getMemberById(memberId);
        member.modifyUsername(newUsername);
    }

    @Transactional
    public void modifyPassword(Long memberId, Password newPassword) {
        final Member member = getMemberById(memberId);
        member.modifyPassword(newPassword);
    }

    @Transactional
    public void resetPassword(ValidatedPasswordResetRequest validatedRequest) {
        final Member member = getMemberByEmail(validatedRequest.getUniversityEmail());
        member.modifyPassword(validatedRequest.getPassword());
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

    public void throwIfNotExistEmail(UniversityEmail email) {
        if (!memberRepository.existsByUniversityEmail(email)) {
            throw new TingApplicationException(MEMBER_015);
        }
    }

    public void throwIfExistKakaoId(KakaoId kakaoId) {
        if (memberRepository.existsByKakaoId(kakaoId)) {
            throw new TingApplicationException(MEMBER_008);
        }
    }

    public void throwIfNotValidatePassword(Long memberId, Password password) {
        final Member member = getMemberById(memberId);
        if (!password.equals(member.getPassword())) {
            throw new TingApplicationException(MEMBER_016);
        }
    }

    public void throwIfNotValidateToken(HttpServletRequest request) {
        final String accessToken = tokenService.getAccessTokenFromHeader(request);
        tokenService.validateToken(accessToken);
    }

    public MemberInfoResponse findMemberInfo(Long memberId) {
        final Member member = getMemberById(memberId);
        return MemberInfoResponse.from(member);
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
