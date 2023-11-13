package com.everyTing.photo.domain.data;

import com.everyTing.core.exception.TingApplicationException;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import static com.everyTing.photo.domain.data.constraints.PhotoConstraints.GENERATED_IMG_URLS_COUNT;
import static com.everyTing.photo.errorCode.PhotoErrorCode.PHOTO_001;
import static com.everyTing.photo.errorCode.PhotoErrorCode.PHOTO_003;

@Embeddable
public class GeneratedImgUrls {

    @NotNull
    @Column(name = "generated_img_urls", columnDefinition = "TEXT")
    private String value;

    private static final String DELIMITER = ",,,";

    protected GeneratedImgUrls() {
    }

    private GeneratedImgUrls(String value) {
        this.value = value;
    }

    private static String from(String value) {
        final String trimValue = value.trim();
        validate(trimValue);
        return trimValue;
    }

    public static GeneratedImgUrls from(List<String> values) {
        validate(values);

        final String generateImgUrls = values.stream()
                .map(GeneratedImgUrls::from)
                .collect(Collectors.joining(DELIMITER));

        return new GeneratedImgUrls(generateImgUrls);
    }

    private static void validate(List<String> values) {
        if (values.size() != GENERATED_IMG_URLS_COUNT) {
            throw new TingApplicationException(PHOTO_001);
        }
    }

    private static void validate(String value) {
        if (!StringUtils.hasText(value)) {
            throw new TingApplicationException(PHOTO_003);
        }
    }

    public List<String> getGeneratedImgUrlList() {
        return List.of(value.split(DELIMITER));
    }
}