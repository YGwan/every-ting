package com.everyTing.member.dto.request;

import lombok.Getter;

@Getter
public class EmailAuthCodeValidateRequest {

    private String email;

    private String authCode;

    public EmailAuthCodeValidateRequest() {
    }

    public EmailAuthCodeValidateRequest(String email, String authCode) {
        this.email = email;
        this.authCode = authCode;
    }
}

