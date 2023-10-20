package com.everyTing.member.dto.request;

import lombok.Getter;

@Getter
public class PasswordModifyRequest {

    private String password;

    private String newPassword;

    public PasswordModifyRequest() {
    }

    public PasswordModifyRequest(String password, String newPassword) {
        this.password = password;
        this.newPassword = newPassword;
    }
}
