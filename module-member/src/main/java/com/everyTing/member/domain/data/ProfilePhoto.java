package com.everyTing.member.domain.data;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.member.domain.converter.MemberDataEncryptedConverter;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import static com.everyTing.member.errorCode.MemberErrorCode.MEMBER_018;

@Embeddable
public class ProfilePhoto {

    @NotNull
    @Column(name = "profile_photo")
    @Convert(converter = MemberDataEncryptedConverter.class)
    private String value;

    protected ProfilePhoto() {
    }

    private ProfilePhoto(String value) {
        this.value = value;
    }

    public static ProfilePhoto from(String value) {
        final String trimValue = value.trim();
        validate(trimValue);
        return new ProfilePhoto(trimValue);
    }

    public static void validate(String value) {
        if (!StringUtils.hasText(value)) {
            throw new TingApplicationException(MEMBER_018);
        }
    }

    public String getValue() {
        return value;
    }
}
