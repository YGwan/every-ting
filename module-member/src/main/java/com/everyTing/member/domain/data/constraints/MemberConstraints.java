package com.everyTing.member.domain.data.constraints;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MemberConstraints {

    public static int USERNAME_MIN_LENGTH;
    public static int USERNAME_MAX_LENGTH;
    public static String PASSWORD_PATTERN;
    public static String EMAIL_PATTERN;
    public static int YEAR_MIN_LIMIT;
    public static int YEAR_MAX_LIMIT;
    public static int GENERATED_IMG_URLS_COUNT;

    public MemberConstraints(
            @Value("${username.length.min}")
            int usernameMinLength,
            @Value("${username.length.max}")
            int usernameMaxLength,
            @Value("${password.pattern}")
            String passwordPattern,
            @Value("${email.pattern}")
            String emailPattern,
            @Value("${year.limit.min}")
            int yearLimitMin,
            @Value("${year.limit.max}")
            int yearLimitMax,
            @Value("${generated.img.urls.count}")
            int generatedImgUrlsCount
    ) {
        USERNAME_MIN_LENGTH = usernameMinLength;
        USERNAME_MAX_LENGTH = usernameMaxLength;
        PASSWORD_PATTERN = passwordPattern;
        EMAIL_PATTERN = emailPattern;
        YEAR_MIN_LIMIT = yearLimitMin;
        YEAR_MAX_LIMIT = yearLimitMax;
        GENERATED_IMG_URLS_COUNT = generatedImgUrlsCount;
    }
}
