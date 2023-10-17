package com.everyTing.member.domain.data.constraints;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MemberConstraints {

    public static int USERNAME_MIN_LENGTH;
    public static int USERNAME_MAX_LENGTH;
    public static String PASSWORD_PATTERN;
    public static String EMAIL_PATTERN;

    public MemberConstraints(
            @Value("${username.length.min}")
            int usernameMinLength,
            @Value("${username.length.max}")
            int usernameMaxLength,
            @Value("${password.pattern}")
            String passwordPattern,
            @Value("${email.pattern}")
            String emailPattern
    ) {
        USERNAME_MIN_LENGTH = usernameMinLength;
        USERNAME_MAX_LENGTH = usernameMaxLength;
        PASSWORD_PATTERN = passwordPattern;
        EMAIL_PATTERN = emailPattern;
    }
}
