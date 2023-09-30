package com.everyTing.team.adapter.out.persistence.entity.embedded;

import static com.everyTing.team.common.constraints.TeamConstraints.HASHTAG_MAX_LENGTH;
import static com.everyTing.team.common.constraints.TeamConstraints.HASHTAG_MIN_LENGTH;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_004;

import com.everyTing.core.exception.TingApplicationException;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Hashtag {

    @Column(name = "content", length = 10)
    private String value;

    protected Hashtag() {
    }

    private Hashtag(String value) {
        this.value = value;
    }

    public static Hashtag from(String value) {
        String trimValue = value.trim();
        validate(trimValue);
        return new Hashtag(trimValue);
    }

    public static void validate(String value) {
        if (!(value.length() >= HASHTAG_MIN_LENGTH && value.length() <= HASHTAG_MAX_LENGTH)) {
            throw new TingApplicationException(TEAM_004);
        }
    }

    public String getValue() {
        return value;
    }
}
