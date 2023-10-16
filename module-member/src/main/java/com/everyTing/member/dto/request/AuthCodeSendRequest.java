package com.everyTing.member.dto.request;

import lombok.Getter;

@Getter
public class AuthCodeSendRequest {

    private final String username;

    private final String universityEmail;

    public AuthCodeSendRequest(String username, String universityEmail) {
        this.username = username;
        this.universityEmail = universityEmail;
    }
}
