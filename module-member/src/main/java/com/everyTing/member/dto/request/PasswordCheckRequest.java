package com.everyTing.member.dto.request;

import lombok.Getter;

@Getter
public class PasswordCheckRequest {

    private String password;

    public PasswordCheckRequest() {
    }

    public PasswordCheckRequest(String password) {
        this.password = password;
    }
}
