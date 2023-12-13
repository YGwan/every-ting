package com.everyTing.photo.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class GeneratedPhotoAddRequest {

    private Long memberId;

    private List<String> generatedImgUrls;

    public GeneratedPhotoAddRequest() {
    }

    public GeneratedPhotoAddRequest(Long memberId, List<String> generatedImgUrls) {
        this.memberId = memberId;
        this.generatedImgUrls = generatedImgUrls;
    }
}
