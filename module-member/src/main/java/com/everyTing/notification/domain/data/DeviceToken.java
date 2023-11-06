package com.everyTing.notification.domain.data;

import com.everyTing.core.exception.TingApplicationException;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import static com.everyTing.notification.errorCode.NotificationErrorCode.NOTIFICATION_001;

@Embeddable
public class DeviceToken {

    @NotNull
    @Column(name = "device_token", unique = true)
    private String value;

    protected DeviceToken() {
    }

    private DeviceToken(String value) {
        this.value = value;
    }

    public static DeviceToken from(String value) {
        final String trimValue = value.trim();
        validate(trimValue);
        return new DeviceToken(trimValue);
    }

    public static void validate(String value) {
        if (!StringUtils.hasText(value)) {
            throw new TingApplicationException(NOTIFICATION_001);
        }
    }

    public String getValue() {
        return value;
    }
}
