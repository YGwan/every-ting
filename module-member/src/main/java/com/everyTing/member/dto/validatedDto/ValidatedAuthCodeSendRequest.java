package com.everyTing.member.dto.validatedDto;

import com.everyTing.member.domain.data.UniversityEmail;
import com.everyTing.member.domain.data.Username;
import com.everyTing.member.dto.request.AuthCodeSendRequest;
import lombok.Getter;

@Getter
public class ValidatedAuthCodeSendRequest {

    private final Username username;

    private final UniversityEmail universityEmail;

    public ValidatedAuthCodeSendRequest(Username username, UniversityEmail universityEmail) {
        this.username = username;
        this.universityEmail = universityEmail;
    }

    public static ValidatedAuthCodeSendRequest from(AuthCodeSendRequest request) {
        return new ValidatedAuthCodeSendRequest(
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
