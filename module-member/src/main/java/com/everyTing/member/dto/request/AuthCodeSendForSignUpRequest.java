package com.everyTing.member.dto.request;

import lombok.Getter;

@Getter
public class AuthCodeSendForSignUpRequest {

    private String username;

    private String universityEmail;

    public AuthCodeSendForSignUpRequest() {
    }

    public AuthCodeSendForSignUpRequest(String username, String universityEmail) {
        this.username = username;
        this.universityEmail = universityEmail;
    }
}
