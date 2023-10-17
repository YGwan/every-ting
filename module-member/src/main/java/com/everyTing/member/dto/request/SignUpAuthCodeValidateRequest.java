package com.everyTing.member.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class SignUpAuthCodeValidateRequest {

    private String email;

    @NotBlank
    private String authCode;

    public SignUpAuthCodeValidateRequest() {
    }

    public SignUpAuthCodeValidateRequest(String email, String authCode) {
        this.email = email;
        this.authCode = authCode;
    }
}

