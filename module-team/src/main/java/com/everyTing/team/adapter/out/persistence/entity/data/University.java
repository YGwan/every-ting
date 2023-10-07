package com.everyTing.team.adapter.out.persistence.entity.data;

import static com.everyTing.team.common.exception.errorCode.TeamServerErrorCode.TSER_003;

import com.everyTing.core.exception.TingServerException;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.springframework.util.StringUtils;

@Embeddable
public class University {

    @Column(name = "university", nullable = false)
    private String value;

    protected University() {
    }

    private University(String value) {
        this.value = value;
    }

    public static University from(String value) {
        String trimValue = value.trim();
        validate(trimValue);
        return new University(trimValue);
    }

    public static void validate(String value) {
        if (!StringUtils.hasText(value)) {
            throw new TingServerException(TSER_003);
        }
    }

    public String getValue() {
        return value;
    }
}
