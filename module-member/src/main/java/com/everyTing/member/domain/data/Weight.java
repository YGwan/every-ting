package com.everyTing.member.domain.data;

import com.everyTing.core.exception.TingApplicationException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import static com.everyTing.member.domain.data.constraints.MemberConstraints.WEIGHT_MAX;
import static com.everyTing.member.domain.data.constraints.MemberConstraints.WEIGHT_MIN;
import static com.everyTing.member.errorCode.MemberErrorCode.MEMBER_007;

@Embeddable
public class Weight {

    @NotNull
    @Column(name = "Weight")
    private double value;

    protected Weight() {
    }

    private Weight(double value) {
        this.value = value;
    }

    public static Weight from(double value) {
        final double weight = Math.round((value * 10) / 10.0);
        validate(weight);
        return new Weight(weight);
    }

    public static void validate(double weight) {
        if (!(weight >= WEIGHT_MIN && weight <= WEIGHT_MAX)) {
            throw new TingApplicationException(MEMBER_007);
        }
    }

    public double getValue() {
        return value;
    }
}

