package com.everyTing.photo.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class GeneratedImgUrlsSaveRequest {

    private Long userId;

    private List<String> generatedImgUrls;

    public GeneratedImgUrlsSaveRequest() {
    }

    public GeneratedImgUrlsSaveRequest(Long userId, List<String> generatedImgUrls) {
        this.userId = userId;
        this.generatedImgUrls = generatedImgUrls;
    }
}
