package com.everyTing.member.domain.data;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.member.domain.converter.MemberStringDataEncryptedConverter;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import static com.everyTing.member.errorCode.MemberErrorCode.MEMBER_003;

@Embeddable
public class University {

    @NotNull
    @Column(name = "university")
    @Convert(converter = MemberStringDataEncryptedConverter.class)
    private String value;

    protected University() {
    }

    private University(String value) {
        this.value = value;
    }

    public static University from(String value) {
        final String trimValue = value.trim();
        validate(trimValue);
        return new University(trimValue);
    }

    public static void validate(String value) {
        if (!StringUtils.hasText(value)) {
            throw new TingApplicationException(MEMBER_003);
        }
    }

    public String getValue() {
        return value;
    }
}
