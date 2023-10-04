package com.everyTing.member.domain.data.constraints;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MemberConstraints {

    public static int USERNAME_MIN_LENGTH;
    public static int USERNAME_MAX_LENGTH;
    public static double HEIGHT_MIN;
    public static double HEIGHT_MAX;
    public static double WEIGHT_MIN;
    public static double WEIGHT_MAX;
    public static String PASSWORD_PATTERN;
    public static String EMAIL_PATTERN;

    public MemberConstraints(
            @Value("${username.length.min}")
            int usernameMinLength,
            @Value("${username.length.max}")
            int usernameMaxLength,
            @Value("${height.limit.min}")
            double heightMinLimit,
            @Value("${height.limit.max}")
            double heightMaxLimit,
            @Value("${weight.limit.min}")
            double weightMinLimit,
            @Value("${weight.limit.max}")
            double weightMaxLimit,
            @Value("${password.pattern}")
            String passwordPattern,
            @Value("${email.pattern}")
            String emailPattern
    ) {
        USERNAME_MIN_LENGTH = usernameMinLength;
        USERNAME_MAX_LENGTH = usernameMaxLength;
        HEIGHT_MIN = heightMinLimit;
        HEIGHT_MAX = heightMaxLimit;
        WEIGHT_MIN = weightMinLimit;
        WEIGHT_MAX = weightMaxLimit;
        PASSWORD_PATTERN = passwordPattern;
        EMAIL_PATTERN = emailPattern;
    }
}
