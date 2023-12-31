package com.everyTing.team.adapter.out.persistence.entity.data;

import static com.everyTing.team.common.constraints.TeamConstraints.REGIONS;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_003;

import com.everyTing.core.exception.TingApplicationException;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Region {

    @Column(name = "region", nullable = false, length = 5)
    private String value;

    protected Region() {
    }

    private Region(String value) {
        this.value = value;
    }

    public static Region from(String value) {
        String trimValue = value.trim();
        validate(trimValue);
        return new Region(trimValue);
    }

    public static void validate(String value) {
        if (!REGIONS.contains(value)) {
            throw new TingApplicationException(TEAM_003);
        }
    }

    public String getValue() {
        return value;
    }
}
