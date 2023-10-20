package com.everyTing.member.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class EmailAuthCodeValidateRequest {

    private String email;

    @NotBlank
    private String authCode;

    public EmailAuthCodeValidateRequest() {
    }

    public EmailAuthCodeValidateRequest(String email, String authCode) {
        this.email = email;
        this.authCode = authCode;
    }
}

