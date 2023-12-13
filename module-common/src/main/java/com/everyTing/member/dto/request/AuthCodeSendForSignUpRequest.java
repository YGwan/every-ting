package com.everyTing.member.dto.request;

import com.everyTing.member.domain.data.UniversityEmail;
import com.everyTing.member.domain.data.Username;
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

    public Username usernameEntity() {
        return Username.from(username);
    }

    public UniversityEmail universityEmailEntity() {
        return UniversityEmail.from(universityEmail);
    }
}
