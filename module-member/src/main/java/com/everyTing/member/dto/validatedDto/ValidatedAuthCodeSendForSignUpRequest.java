package com.everyTing.member.dto.validatedDto;

import com.everyTing.member.domain.data.UniversityEmail;
import com.everyTing.member.domain.data.Username;
import com.everyTing.member.dto.request.AuthCodeSendForSignUpRequest;
import lombok.Getter;

@Getter
public class ValidatedAuthCodeSendForSignUpRequest {

    private final Username username;

    private final UniversityEmail universityEmail;

    public ValidatedAuthCodeSendForSignUpRequest(Username username, UniversityEmail universityEmail) {
        this.username = username;
        this.universityEmail = universityEmail;
    }

    public static ValidatedAuthCodeSendForSignUpRequest from(AuthCodeSendForSignUpRequest request) {
        return new ValidatedAuthCodeSendForSignUpRequest(
                Username.from(request.getUsername()),
                UniversityEmail.from(request.getUniversityEmail())
        );
    }

    public String getUsernameValue() {
        return username.getValue();
    }

    public String getUniversityEmailValue() {
        return universityEmail.getValue();
    }
}
