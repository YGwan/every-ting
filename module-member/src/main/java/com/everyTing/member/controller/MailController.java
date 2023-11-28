package com.everyTing.member.controller;

import com.everyTing.core.dto.Response;
import com.everyTing.member.domain.data.UniversityEmail;
import com.everyTing.member.dto.request.AuthCodeSendForResetPasswordRequest;
import com.everyTing.member.dto.request.AuthCodeSendForSignUpRequest;
import com.everyTing.member.dto.request.EmailAuthCodeValidateRequest;
import com.everyTing.member.service.member.MemberService;
import com.everyTing.member.service.mail.MailVerificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/mail")
@RestController
public class MailController {

    private final MailVerificationService mailVerificationService;
    private final MemberService memberService;

    public MailController(MailVerificationService mailVerificationService, MemberService memberService) {
        this.mailVerificationService = mailVerificationService;
        this.memberService = memberService;
    }

    @PostMapping("/signUp/send")
    public Response<Void> authCodeSendForSignUp(@RequestBody AuthCodeSendForSignUpRequest request) {
        memberService.throwIfAlreadyExistedEmail(request.universityEmailEntity());
        mailVerificationService.sendAuthCodeForSignUp(request);
        return Response.success();
    }

    @PostMapping("/password/reset/send")
    public Response<Void> authCodeSendForResetPassword(@RequestBody AuthCodeSendForResetPasswordRequest request) {
        final var validatedUniversityEmail = UniversityEmail.from(request.getUniversityEmail());

        memberService.throwIfNotExisted(validatedUniversityEmail);
        mailVerificationService.sendAuthCodeForResetPassword(validatedUniversityEmail);
        return Response.success();
    }

    @PostMapping("/auth/verify")
    public Response<Void> emailAuthCodeValidate(@RequestBody EmailAuthCodeValidateRequest request) {
        mailVerificationService.validateEmailAuthCode(request.getEmail(), request.getAuthCode());
        return Response.success();
    }
}
