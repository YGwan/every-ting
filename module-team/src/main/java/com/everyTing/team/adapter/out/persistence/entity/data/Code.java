package com.everyTing.team.adapter.out.persistence.entity.data;

import static com.everyTing.team.common.exception.errorCode.TeamServerErrorCode.TSER_002;

import com.everyTing.core.exception.TingServerException;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.springframework.util.StringUtils;

@Embeddable
public class Code {

    @Column(name = "code", unique = true, nullable = false)
    private String value;

    protected Code() {
    }

    private Code(String value) {
        this.value = value;
    }

    public static Code from(String value) {
        String trimValue = value.trim();
        validate(trimValue);
        return new Code(trimValue);
    }

    public static void validate(String value) {
        if (!StringUtils.hasText(value)) {
            throw new TingServerException(TSER_002);
        }
    }

    public String getValue() {
        return value;
    }
}
