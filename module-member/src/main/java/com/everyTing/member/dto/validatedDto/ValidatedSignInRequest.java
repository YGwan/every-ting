package com.everyTing.member.dto.validatedDto;

import com.everyTing.member.domain.data.Password;
import com.everyTing.member.domain.data.UniversityEmail;
import com.everyTing.member.dto.request.SignInRequest;
import lombok.Getter;

@Getter
public class ValidatedSignInRequest {

    private final UniversityEmail email;

    private final Password password;

    public ValidatedSignInRequest(UniversityEmail email, Password password) {
        this.email = email;
        this.password = password;
    }

    public static ValidatedSignInRequest from(SignInRequest request) {
        return new ValidatedSignInRequest(
                UniversityEmail.from(request.getUniversityEmail()),
                Password.from(request.getPassword())
        );
    }
}
