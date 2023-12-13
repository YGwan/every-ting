package com.everyTing.member.dto.request;

import lombok.Getter;

@Getter
public class PasswordModifyRequest {

    private String password;

    public PasswordModifyRequest() {
    }

    public PasswordModifyRequest(String password) {
        this.password = password;
    }
}
