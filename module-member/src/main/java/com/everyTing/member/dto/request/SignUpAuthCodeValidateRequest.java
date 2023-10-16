package com.everyTing.member.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class SignUpAuthCodeValidateRequest {

    private final String email;

    @NotBlank
    private final String authCode;

    public SignUpAuthCodeValidateRequest(String email, String authCode) {
        this.email = email;
        this.authCode = authCode;
    }
}

