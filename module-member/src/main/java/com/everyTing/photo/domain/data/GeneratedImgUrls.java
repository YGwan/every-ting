package com.everyTing.photo.domain.data;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.photo.domain.converter.GeneratedImgUrlsConverter;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.everyTing.photo.domain.data.constraints.PhotoConstraints.GENERATED_IMG_URLS_COUNT;
import static com.everyTing.photo.errorCode.PhotoErrorCode.PHOTO_001;
import static com.everyTing.photo.errorCode.PhotoErrorCode.PHOTO_003;

@Embeddable
public class GeneratedImgUrls {

    @NotNull
    @Column(name = "generated_img_urls", columnDefinition = "TEXT")
    @Convert(converter = GeneratedImgUrlsConverter.class)
    private List<String> value;

    protected GeneratedImgUrls() {
    }

    public GeneratedImgUrls(List<String> value) {
        this.value = value;
    }

    public static String from(String value) {
        final String trimValue = value.trim();
        validate(trimValue);
        return trimValue;
    }

    public static void validate(List<String> values) {
        if (values.size() != GENERATED_IMG_URLS_COUNT) {
            throw new TingApplicationException(PHOTO_001);
        }
    }

    private static void validate(String value) {
        if (!StringUtils.hasText(value)) {
            throw new TingApplicationException(PHOTO_003);
        }
    }

    public List<String> getValue() {
        return value;
    }
}