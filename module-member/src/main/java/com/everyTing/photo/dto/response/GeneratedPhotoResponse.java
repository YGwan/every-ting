package com.everyTing.photo.dto.response;

import lombok.Getter;

@Getter
public class GeneratedPhotoResponse {

    private String generatedImgUrls;

    public GeneratedPhotoResponse() {
    }

    public GeneratedPhotoResponse(String generatedImgUrls) {
        this.generatedImgUrls = generatedImgUrls;
    }
}
