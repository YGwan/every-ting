package com.everyTing.photo.dto.response;

import com.everyTing.photo.domain.GeneratedPhoto;
import lombok.Getter;

@Getter
public class GeneratedPhotoResponse {

    private String generatedImgUrls;

    public GeneratedPhotoResponse() {
    }

    public GeneratedPhotoResponse(String generatedImgUrls) {
        this.generatedImgUrls = generatedImgUrls;
    }

    public static GeneratedPhotoResponse from(GeneratedPhoto generatedPhoto) {
        return new GeneratedPhotoResponse(
                generatedPhoto.getGeneratedImgUrls().getValue()
        );
    }
}
