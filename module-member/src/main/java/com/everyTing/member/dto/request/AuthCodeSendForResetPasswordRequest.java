package com.everyTing.member.dto.request;

import lombok.Getter;

@Getter
public class AuthCodeSendForResetPasswordRequest {

    private String universityEmail;

    public AuthCodeSendForResetPasswordRequest() {
    }

    public AuthCodeSendForResetPasswordRequest(String universityEmail) {
        this.universityEmail = universityEmail;
    }
}
