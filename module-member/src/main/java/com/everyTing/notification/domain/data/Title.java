package com.everyTing.notification.domain.data;

import com.everyTing.core.exception.TingApplicationException;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import static com.everyTing.notification.errorCode.NotificationServerErrorCode.NSER_003;

@Embeddable
public class Title {

    @NotNull
    @Column(name = "title")
    private String value;

    protected Title() {
    }

    private Title(String value) {
        this.value = value;
    }

    public static Title from(String value) {
        final String trimValue = value.trim();
        validate(trimValue);
        return new Title(trimValue);
    }

    public static void validate(String value) {
        if (!StringUtils.hasText(value)) {
            throw new TingApplicationException(NSER_003);
        }
    }

    public String getValue() {
        return value;
    }
}

