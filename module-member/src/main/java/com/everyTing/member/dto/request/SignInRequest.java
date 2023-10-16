package com.everyTing.member.dto.request;

import lombok.Getter;

@Getter
public class SignInRequest {

    private final String universityEmail;

    private final String password;

    public SignInRequest(String universityEmail, String password) {
        this.universityEmail = universityEmail;
        this.password = password;
    }
}
