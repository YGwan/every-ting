package com.everyTing.member.domain.data;

import com.everyTing.core.exception.TingApplicationException;
import lombok.Getter;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import static com.everyTing.member.errorCode.MemberErrorCode.MEMBER_002;

@Getter
@Embeddable
public class UniversityEmail {


    @NotNull
    @Email
    @Column(name = "university_email", unique = true)
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
        if (!StringUtils.hasText(value)) {
            throw new TingApplicationException(MEMBER_002);
        }
    }
}
