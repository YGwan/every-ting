package com.everyTing.team.adapter.out.persistence.entity.embedded;

import static com.everyTing.team.common.constraints.LimitConstraints.NAME_MAX_LENGTH;
import static com.everyTing.team.common.constraints.LimitConstraints.NAME_MIN_LENGTH;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_001;

import com.everyTing.core.exception.TingApplicationException;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Name {

    @Column(name = "name")
    private String value;

    protected Name() {
    }

    private Name(String value) {
        this.value = value;
    }

    public static Name from(String value) {
        String trimValue = value.trim();
        validate(trimValue);
        return new Name(trimValue);
    }

    public static void validate(String value) {
        if (!(value.length() >= NAME_MIN_LENGTH && value.length() <= NAME_MAX_LENGTH)) {
            throw new TingApplicationException(TEAM_001);
        }
    }

    public String getValue() {
        return value;
    }
}
