package com.everyTing.member.service.mail;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.member.cache.EmailAuthCodeCache;
import com.everyTing.member.cache.EmailAuthCodeCacheRepository;
import com.everyTing.member.domain.data.UniversityEmail;
import com.everyTing.member.dto.validatedDto.ValidatedAuthCodeSendForSignUpRequest;
import com.everyTing.member.service.mail.form.ResetPasswordForm;
import com.everyTing.member.service.mail.form.SignUpForm;
import com.everyTing.member.utils.RandomCodeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.everyTing.member.errorCode.MemberErrorCode.MEMBER_013;

@Transactional
@Service
public class MailVerificationService {

    private final MailService mailService;
    private final EmailAuthCodeCacheRepository emailAuthCodeCacheRepository;

    public MailVerificationService(MailService mailService, EmailAuthCodeCacheRepository emailAuthCodeCacheRepository) {
        this.mailService = mailService;
        this.emailAuthCodeCacheRepository = emailAuthCodeCacheRepository;
    }

    public void sendAuthCodeForSignUp(ValidatedAuthCodeSendForSignUpRequest request) {
        final String username = request.getUsernameValue();
        final String universityEmail = request.getUniversityEmailValue();
        final String emailAuthCode = RandomCodeUtils.generate();

        mailService.sendMail(universityEmail, new SignUpForm(username, emailAuthCode));
        emailAuthCodeCacheRepository.save(new EmailAuthCodeCache(universityEmail, emailAuthCode));
    }

    public void sendAuthCodeForResetPassword(UniversityEmail email) {
        final String universityEmail = email.getValue();
        final String emailAuthCode = RandomCodeUtils.generate();

        mailService.sendMail(universityEmail, new ResetPasswordForm(emailAuthCode));
        emailAuthCodeCacheRepository.save(new EmailAuthCodeCache(universityEmail, emailAuthCode));
    }

    @Transactional(readOnly = true)
    public void validateEmailAuthCode(String email, String authCode) {
        final EmailAuthCodeCache emailAuthCodeCache = emailAuthCodeCacheRepository.findById(email).orElseThrow(() ->
                new TingApplicationException(MEMBER_013)
        );
        emailAuthCodeCache.checkAuthCodeSame(authCode);
        emailAuthCodeCache.checkEmailSame(email);
    }
}
