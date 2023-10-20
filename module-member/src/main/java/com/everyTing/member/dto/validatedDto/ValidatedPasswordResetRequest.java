package com.everyTing.member.dto.validatedDto;

import com.everyTing.member.domain.data.Password;
import com.everyTing.member.domain.data.UniversityEmail;
import com.everyTing.member.dto.request.PasswordResetRequest;
import lombok.Getter;

@Getter
public class ValidatedPasswordResetRequest {

    private final UniversityEmail universityEmail;

    private final Password password;

    public ValidatedPasswordResetRequest(UniversityEmail universityEmail, Password password) {
        this.universityEmail = universityEmail;
        this.password = password;
    }

    public static ValidatedPasswordResetRequest from(PasswordResetRequest request) {
        return new ValidatedPasswordResetRequest(
                UniversityEmail.from(request.getUniversityEmail()),
                Password.from(request.getPassword())
        );
    }
}
