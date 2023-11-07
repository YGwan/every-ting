package com.everyTing.member.dto.request;

import lombok.Getter;

@Getter
public class SignInRequest {

    private String universityEmail;

    private String password;

    private String pushToken;

    public SignInRequest() {
    }

    public SignInRequest(String universityEmail, String password, String pushToken) {
        this.universityEmail = universityEmail;
        this.password = password;
        this.pushToken = pushToken;
    }
}
