package com.everyTing.member.service.mail;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.member.cache.EmailAuthCodeCache;
import com.everyTing.member.cache.EmailAuthCodeCacheRepository;
import com.everyTing.member.domain.data.UniversityEmail;
import com.everyTing.member.dto.form.ResetPasswordForm;
import com.everyTing.member.dto.form.SignUpForm;
import com.everyTing.member.dto.request.AuthCodeSendForSignUpRequest;
import com.everyTing.member.utils.RandomCodeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.everyTing.member.errorCode.MemberErrorCode.MEMBER_011;
import static com.everyTing.member.errorCode.MemberErrorCode.MEMBER_012;

@Transactional
@Service
public class MailVerificationService {

    private final MailService mailService;
    private final EmailAuthCodeCacheRepository emailAuthCodeCacheRepository;

    public MailVerificationService(MailService mailService, EmailAuthCodeCacheRepository emailAuthCodeCacheRepository) {
        this.mailService = mailService;
        this.emailAuthCodeCacheRepository = emailAuthCodeCacheRepository;
    }

    public void sendAuthCodeForSignUp(AuthCodeSendForSignUpRequest request) {
        final String username = request.getUsername();
        final String universityEmail = request.getUniversityEmail();
        final String emailAuthCode = RandomCodeUtils.generate();

        emailAuthCodeCacheRepository.save(new EmailAuthCodeCache(universityEmail, emailAuthCode));
        mailService.sendMail(universityEmail, new SignUpForm(username, emailAuthCode));
    }

    public void sendAuthCodeForResetPassword(UniversityEmail email) {
        final String universityEmail = email.getValue();
        final String emailAuthCode = RandomCodeUtils.generate();

        emailAuthCodeCacheRepository.save(new EmailAuthCodeCache(universityEmail, emailAuthCode));
        mailService.sendMail(universityEmail, new ResetPasswordForm(emailAuthCode));
    }

    @Transactional(readOnly = true)
    public void validateEmailAuthCode(String email, String authCode) {
        final EmailAuthCodeCache emailAuthCodeCache = emailAuthCodeCacheRepository.findById(email).orElseThrow(() ->
                new TingApplicationException(MEMBER_011)
        );

        final String storedAuthCode = emailAuthCodeCache.getAuthCode();

        if (!authCode.equals(storedAuthCode)) {
            throw new TingApplicationException(MEMBER_012);
        }
    }
}
