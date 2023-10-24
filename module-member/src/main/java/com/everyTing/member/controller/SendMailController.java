package com.everyTing.member.controller;

import com.everyTing.core.dto.Response;
import com.everyTing.member.domain.data.UniversityEmail;
import com.everyTing.member.dto.request.AuthCodeSendForResetPasswordRequest;
import com.everyTing.member.dto.request.AuthCodeSendForSignUpRequest;
import com.everyTing.member.dto.request.EmailAuthCodeValidateRequest;
import com.everyTing.member.dto.validatedDto.ValidatedAuthCodeSendForSignUpRequest;
import com.everyTing.member.service.MemberValidateCheckService;
import com.everyTing.member.service.mail.SendMailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/mail/send")
@RestController
public class SendMailController {

    private final SendMailService sendMailService;
    private final MemberValidateCheckService memberValidateCheckService;

    public SendMailController(SendMailService sendMailService, MemberValidateCheckService memberValidateCheckService) {
        this.sendMailService = sendMailService;
        this.memberValidateCheckService = memberValidateCheckService;
    }

    @PostMapping("/signUp")
    public Response<Void> authCodeSendForSignUp(@RequestBody AuthCodeSendForSignUpRequest request) {
        final var validatedRequest = ValidatedAuthCodeSendForSignUpRequest.from(request);

        memberValidateCheckService.throwIfExistEmail(validatedRequest.getUniversityEmail());
        sendMailService.sendAuthCodeForSignUp(validatedRequest);
        return Response.success();
    }

    @PostMapping("/password/reset")
    public Response<Void> authCodeSendForResetPassword(@RequestBody AuthCodeSendForResetPasswordRequest request) {
        final var validatedUniversityEmail = UniversityEmail.from(request.getUniversityEmail());

        memberValidateCheckService.throwIfNotExistEmail(validatedUniversityEmail);
        sendMailService.sendAuthCodeForResetPassword(validatedUniversityEmail);
        return Response.success();
    }

    @PostMapping("/auth/verify")
    public Response<Void> emailAuthCodeValidate(@RequestBody EmailAuthCodeValidateRequest request) {
        sendMailService.validateEmailAuthCode(request.getEmail(), request.getAuthCode());
        return Response.success();
    }
}
