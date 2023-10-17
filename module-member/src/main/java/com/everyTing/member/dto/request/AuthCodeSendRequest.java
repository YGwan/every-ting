package com.everyTing.member.dto.request;

import lombok.Getter;

@Getter
public class AuthCodeSendRequest {

    private String username;

    private String universityEmail;

    public AuthCodeSendRequest() {
    }

    public AuthCodeSendRequest(String username, String universityEmail) {
        this.username = username;
        this.universityEmail = universityEmail;
    }
}
