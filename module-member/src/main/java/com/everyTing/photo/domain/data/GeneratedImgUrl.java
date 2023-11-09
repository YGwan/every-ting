package com.everyTing.photo.domain.data;

import com.everyTing.core.exception.TingApplicationException;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import static com.everyTing.photo.errorCode.PhotoErrorCode.PHOTO_003;

@Embeddable
public class GeneratedImgUrl {

    @NotNull
    @Column(name = "generated_img_url")
    private String value;

    protected GeneratedImgUrl() {
    }

    private GeneratedImgUrl(String value) {
        this.value = value;
    }

    public static GeneratedImgUrl from(String value) {
        final String trimValue = value.trim();
        validate(trimValue);
        return new GeneratedImgUrl(trimValue);
    }

    public static void validate(String value) {
        if (!StringUtils.hasText(value)) {
            throw new TingApplicationException(PHOTO_003);
        }
    }

    public String getValue() {
        return value;
    }
}
