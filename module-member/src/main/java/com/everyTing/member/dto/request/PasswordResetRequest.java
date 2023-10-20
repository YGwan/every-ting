package com.everyTing.member.dto.request;

import lombok.Getter;

@Getter
public class PasswordResetRequest {

    private String universityEmail;

    private String password;

    public PasswordResetRequest() {
    }

    public PasswordResetRequest(String universityEmail, String password) {
        this.universityEmail = universityEmail;
        this.password = password;
    }
}
