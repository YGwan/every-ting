package com.everyTing.member.controller;

import com.everyTing.core.dto.Response;
import com.everyTing.member.domain.data.UniversityEmail;
import com.everyTing.member.dto.request.AuthCodeSendForResetPasswordRequest;
import com.everyTing.member.dto.request.AuthCodeSendForSignUpRequest;
import com.everyTing.member.dto.request.EmailAuthCodeValidateRequest;
import com.everyTing.member.dto.validatedDto.ValidatedAuthCodeSendForSignUpRequest;
import com.everyTing.member.service.MemberServiceValidator;
import com.everyTing.member.service.mail.MailVerificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/mail")
@RestController
public class MailController {

    private final MailVerificationService mailVerificationService;
    private final MemberServiceValidator memberServiceValidator;

    public MailController(MailVerificationService mailVerificationService, MemberServiceValidator memberServiceValidator) {
        this.mailVerificationService = mailVerificationService;
        this.memberServiceValidator = memberServiceValidator;
    }

    @PostMapping("/signUp/send")
    public Response<Void> authCodeSendForSignUp(@RequestBody AuthCodeSendForSignUpRequest request) {
        final var validatedRequest = ValidatedAuthCodeSendForSignUpRequest.from(request);

        memberServiceValidator.throwIfAlreadyExisted(validatedRequest.getUniversityEmail());
        mailVerificationService.sendAuthCodeForSignUp(validatedRequest);
        return Response.success();
    }

    @PostMapping("/password/reset/send")
    public Response<Void> authCodeSendForResetPassword(@RequestBody AuthCodeSendForResetPasswordRequest request) {
        final var validatedUniversityEmail = UniversityEmail.from(request.getUniversityEmail());

        memberServiceValidator.throwIfNotExisted(validatedUniversityEmail);
        mailVerificationService.sendAuthCodeForResetPassword(validatedUniversityEmail);
        return Response.success();
    }

    @PostMapping("/auth/verify")
    public Response<Void> emailAuthCodeValidate(@RequestBody EmailAuthCodeValidateRequest request) {
        mailVerificationService.validateEmailAuthCode(request.getEmail(), request.getAuthCode());
        return Response.success();
    }
}
