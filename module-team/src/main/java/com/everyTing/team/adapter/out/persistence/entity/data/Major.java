package com.everyTing.team.adapter.out.persistence.entity.data;

import static com.everyTing.team.common.exception.errorCode.TeamServerErrorCode.TSER_001;

import com.everyTing.core.exception.TingServerException;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.springframework.util.StringUtils;

@Embeddable
public class Major {

    @Column(name = "major", nullable = false)
    private String value;

    protected Major() {
    }

    private Major(String value) {
        this.value = value;
    }

    public static Major from(String value) {
        String trimValue = value.trim();
        validate(trimValue);
        return new Major(trimValue);
    }

    public static void validate(String value) {
        if (!StringUtils.hasText(value)) {
            throw new TingServerException(TSER_001);
        }
    }

    public String getValue() {
        return value;
    }
}
