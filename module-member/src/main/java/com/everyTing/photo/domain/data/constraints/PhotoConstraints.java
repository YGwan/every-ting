package com.everyTing.photo.domain.data.constraints;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PhotoConstraints {

    public static int GENERATED_IMG_URLS_COUNT;

    public PhotoConstraints(
            @Value("${generated.img.urls.count}")
            int generatedImgUrlsCount
    ) {
        GENERATED_IMG_URLS_COUNT = generatedImgUrlsCount;
    }
}
