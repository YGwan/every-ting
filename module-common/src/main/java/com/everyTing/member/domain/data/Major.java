package com.everyTing.member.domain.data;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.member.domain.converter.MemberStringDataEncryptedConverter;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import static com.everyTing.member.errorCode.MemberErrorCode.MEMBER_004;

@Embeddable
public class Major {

    @NotNull
    @Column(name = "major")
    @Convert(converter = MemberStringDataEncryptedConverter.class)
    private String value;

    protected Major() {
    }

    private Major(String value) {
        this.value = value;
    }

    public static Major from(String value) {
        final String trimValue = value.trim();
        validate(trimValue);
        return new Major(trimValue);
    }

    public static void validate(String value) {
        if (!StringUtils.hasText(value)) {
            throw new TingApplicationException(MEMBER_004);
        }
    }

    public String getValue() {
        return value;
    }
}
