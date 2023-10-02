package com.everyTing.member.domain.data;

import com.everyTing.core.exception.TingApplicationException;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import static com.everyTing.member.domain.data.constraints.MemberConstraints.HEIGHT_MAX;
import static com.everyTing.member.domain.data.constraints.MemberConstraints.HEIGHT_MIN;
import static com.everyTing.member.errorCode.MemberErrorCode.MEMBER_006;

@Getter
@Embeddable
public class Height {

    @NotNull
    @Column(name = "height")
    private double value;

    protected Height() {
    }

    private Height(double value) {
        this.value = value;
    }

    public static Height from(double value) {
        double height = Math.round((value * 10) / 10.0);
        validate(height);
        return new Height(height);
    }

    public static void validate(double height) {
        if (!(height >= HEIGHT_MIN && height <= HEIGHT_MAX)) {
            throw new TingApplicationException(MEMBER_006);
        }
    }
}

