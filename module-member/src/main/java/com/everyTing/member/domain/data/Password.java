package com.everyTing.member.domain.data;

import com.everyTing.core.exception.TingApplicationException;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.everyTing.member.domain.data.constraints.MemberConstraints.PASSWORD_PATTERN;
import static com.everyTing.member.errorCode.MemberErrorCode.MEMBER_008;

@Getter
@Embeddable
public class Password {

    @NotNull
    @Column(name = "password")
    private String value;

    protected Password() {
    }

    private Password(String value) {
        this.value = value;
    }

    public static Password from(String value) {
        final String trimValue = value.trim();
        validate(trimValue);
        return new Password(trimValue);
    }

    public static void validate(String value) {
        final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        final Matcher matcher = pattern.matcher(value);
        if (!matcher.matches()) {
            throw new TingApplicationException(MEMBER_008);
        }
    }
}
