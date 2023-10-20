package com.everyTing.member.dto.validatedDto;

import com.everyTing.member.domain.data.Password;
import com.everyTing.member.dto.request.PasswordModifyRequest;
import lombok.Getter;

@Getter
public class ValidatedPasswordModifyRequest {

    private final Password password;

    private final Password newPassword;

    public ValidatedPasswordModifyRequest(Password password, Password newPassword) {
        this.password = password;
        this.newPassword = newPassword;
    }

    public static ValidatedPasswordModifyRequest from(PasswordModifyRequest request) {
        return new ValidatedPasswordModifyRequest(
                Password.from(request.getPassword()),
                Password.from(request.getNewPassword())
        );
    }
}
