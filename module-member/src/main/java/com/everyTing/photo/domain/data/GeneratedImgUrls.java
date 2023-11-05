package com.everyTing.photo.domain.data;

import com.everyTing.core.exception.TingApplicationException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import static com.everyTing.member.domain.data.constraints.MemberConstraints.GENERATED_IMG_URLS_COUNT;
import static com.everyTing.member.errorCode.MemberErrorCode.MEMBER_019;

@Embeddable
public class GeneratedImgUrls {

    @NotNull
    @Column(name = "generated_img_urls", length = 1024)
    private String value;

    protected GeneratedImgUrls() {
    }

    private GeneratedImgUrls(String value) {
        this.value = value;
    }

    public static GeneratedImgUrls from(List<String> values) {
        validate(values);

        final List<String> trimValues = values.stream()
                .map(String::trim)
                .collect(Collectors.toList());

        final String generateImgUrls = String.join(",", trimValues);
        return new GeneratedImgUrls(generateImgUrls);
    }

    public static void validate(List<String> values) {
        if (values.size() != GENERATED_IMG_URLS_COUNT) {
            throw new TingApplicationException(MEMBER_019);
        }
    }

    public String getValue() {
        return value;
    }
}
