package com.everyTing.photo.dto.response;

import com.everyTing.photo.domain.GeneratedPhoto;
import lombok.Getter;

import java.util.List;

@Getter
public class GeneratedPhotoResponse {

    private List<String> generatedImgUrls;

    public GeneratedPhotoResponse() {
    }

    public GeneratedPhotoResponse(List<String> generatedImgUrls) {
        this.generatedImgUrls = generatedImgUrls;
    }

    public static GeneratedPhotoResponse from(GeneratedPhoto generatedPhoto) {
        return new GeneratedPhotoResponse(
                generatedPhoto.getGeneratedImgUrls().getValue()
        );
    }
}
