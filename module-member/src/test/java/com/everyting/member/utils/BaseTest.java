package com.everyting.member.utils;

import com.everyTing.member.domain.data.constraints.MemberConstraints;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.util.ReflectionTestUtils;

public abstract class BaseTest {

    @BeforeAll
    public static void setUpConstraintEnv() {
        ReflectionTestUtils.setField(MemberConstraints.class, "USERNAME_MIN_LENGTH", 2);
        ReflectionTestUtils.setField(MemberConstraints.class, "USERNAME_MAX_LENGTH", 8);
        ReflectionTestUtils.setField(MemberConstraints.class, "PASSWORD_PATTERN", "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{9,16}");
        ReflectionTestUtils.setField(MemberConstraints.class, "EMAIL_PATTERN", "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        ReflectionTestUtils.setField(MemberConstraints.class, "YEAR_MIN_LIMIT", 1990);
        ReflectionTestUtils.setField(MemberConstraints.class, "YEAR_MAX_LIMIT", 2010);
    }
}
