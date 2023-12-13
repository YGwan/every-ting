package com.everyTing.member.dto.request;

import com.everyTing.member.domain.data.UniversityEmail;
import lombok.Getter;

@Getter
public class SignInRequest {

    private String universityEmail;

    private String password;

    public SignInRequest() {
    }

    public SignInRequest(String universityEmail, String password) {
        this.universityEmail = universityEmail;
        this.password = password;
    }

    public UniversityEmail universityEmailEntity() {
        return UniversityEmail.from(universityEmail);
    }
}
