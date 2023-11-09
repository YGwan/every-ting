package com.everyTing.photo.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class GeneratedImgUrlsSaveRequest {

    private Long memberId;

    private List<String> generatedImgUrls;

    public GeneratedImgUrlsSaveRequest() {
    }

    public GeneratedImgUrlsSaveRequest(Long memberId, List<String> generatedImgUrls) {
        this.memberId = memberId;
        this.generatedImgUrls = generatedImgUrls;
    }
}
