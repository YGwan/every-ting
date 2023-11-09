package com.everyTing.notification.domain.data;

import com.everyTing.core.exception.TingApplicationException;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import static com.everyTing.notification.errorCode.NotificationErrorCode.NOTIFICATION_004;

@Embeddable
public class Body {

    @NotNull
    @Column(name = "body")
    private String value;

    protected Body() {
    }

    private Body(String value) {
        this.value = value;
    }

    public static Body from(String value) {
        final String trimValue = value.trim();
        validate(trimValue);
        return new Body(trimValue);
    }

    public static void validate(String value) {
        if (!StringUtils.hasText(value)) {
            throw new TingApplicationException(NOTIFICATION_004);
        }
    }

    public String getValue() {
        return value;
    }
}

