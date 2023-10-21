package com.everyTing.member.domain.data;

import com.everyTing.core.exception.TingApplicationException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import static com.everyTing.member.domain.data.constraints.MemberConstraints.YEAR_MAX_LIMIT;
import static com.everyTing.member.domain.data.constraints.MemberConstraints.YEAR_MIN_LIMIT;
import static com.everyTing.member.errorCode.MemberErrorCode.MEMBER_015;

@Embeddable
public class BirthYear {

    @NotNull
    @Column(name = "birth")
    private Integer value;

    protected BirthYear() {
    }

    private BirthYear(Integer value) {
        this.value = value;
    }

    public static BirthYear from(Integer value) {
        validate(value);
        return new BirthYear(value);
    }

    public static void validate(Integer value) {
        if ((value >= YEAR_MIN_LIMIT && value <= YEAR_MAX_LIMIT)) {
            throw new TingApplicationException(MEMBER_015);
        }
    }

    public Integer getValue() {
        return value;
    }
}
