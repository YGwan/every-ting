package com.everyTing.member.domain.data;

import com.everyTing.core.exception.TingApplicationException;
import lombok.Getter;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import static com.everyTing.member.errorCode.MemberErrorCode.MEMBER_003;

@Getter
@Embeddable
public class University {

    @NotNull
    @Column(name = "university")
    private String value;

    protected University() {
    }

    public University(String value) {
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
}
