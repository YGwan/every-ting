package com.everyTing.team.adapter.out.persistence.entity.data;

import static com.everyTing.team.common.constraints.TeamConstraints.MEMBER_MAX_LIMIT;
import static com.everyTing.team.common.constraints.TeamConstraints.MEMBER_MIN_LIMIT;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_002;

import com.everyTing.core.exception.TingApplicationException;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MemberLimit {

    @Column(name = "member_limit", nullable = false)
    private Short value;

    protected MemberLimit() {
    }

    private MemberLimit(Short value) {
        this.value = value;
    }

    public static MemberLimit from(Short value) {
        validate(value);
        return new MemberLimit(value);
    }

    public static void validate(Short value) {
        if (!(value >= MEMBER_MIN_LIMIT && value <= MEMBER_MAX_LIMIT)) {
            throw new TingApplicationException(TEAM_002);
        }
    }

    public Short getValue() {
        return value;
    }
}
