package com.everyTing.member.dto.request;

import lombok.Getter;

@Getter
public class SendAuthCodeRequest {

    private String username;

    private String universityEmail;

    public SendAuthCodeRequest() {
    }

    public SendAuthCodeRequest(String username, String universityEmail) {
        this.username = username;
        this.universityEmail = universityEmail;
    }
}
