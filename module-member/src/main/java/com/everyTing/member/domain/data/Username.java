package com.everyTing.member.domain.data;


import com.everyTing.core.exception.TingApplicationException;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.everyTing.member.domain.data.constraints.MemberConstraints.USERNAME_MAX_LENGTH;
import static com.everyTing.member.domain.data.constraints.MemberConstraints.USERNAME_MIN_LENGTH;
import static com.everyTing.member.errorCode.MemberErrorCode.MEMBER_001;

@Getter
@Embeddable
public class Username {

    @NotNull
    @Column(name = "username", unique = true)
    private String value;

    protected Username() {
    }

    private Username(String value) {
        this.value = value;
    }

    public static Username from(String value) {
        final String trimValue = value.trim();
        validate(trimValue.length());
        return new Username(trimValue);
    }

    public static void validate(int length) {
        if (!(length >= USERNAME_MIN_LENGTH && length <= USERNAME_MAX_LENGTH)) {
            throw new TingApplicationException(MEMBER_001);
        }
    }
}
