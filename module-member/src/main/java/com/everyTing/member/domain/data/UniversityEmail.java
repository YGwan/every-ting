package com.everyTing.member.domain.data;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.member.domain.converter.MemberStringDataEncryptedConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.everyTing.member.domain.data.constraints.MemberConstraints.EMAIL_PATTERN;
import static com.everyTing.member.errorCode.MemberErrorCode.MEMBER_002;

@Embeddable
public class UniversityEmail {


    @NotNull
    @Column(name = "university_email", unique = true)
    @Convert(converter = MemberStringDataEncryptedConverter.class)
    private String value;

    protected UniversityEmail() {
    }

    private UniversityEmail(String value) {
        this.value = value;
    }

    public static UniversityEmail from(String value) {
        final String trimValue = value.trim();
        validate(trimValue);
        return new UniversityEmail(trimValue);
    }

    public static void validate(String value) {
        final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        final Matcher matcher = pattern.matcher(value);
        if (!matcher.matches()) {
            throw new TingApplicationException(MEMBER_002);
        }
    }

    public String getValue() {
        return value;
    }
}
