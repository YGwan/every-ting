package com.everyTing.photo.dto.validatedRequest;

import com.everyTing.photo.domain.data.GeneratedImgUrls;
import com.everyTing.photo.dto.request.GeneratedImgUrlsSaveRequest;
import lombok.Getter;

@Getter
public class ValidatedGeneratedImgUrlsAddRequest {

    private final Long memberId;

    private final GeneratedImgUrls generatedImgUrls;

    public ValidatedGeneratedImgUrlsAddRequest(Long memberId, GeneratedImgUrls generatedImgUrls) {
        this.memberId = memberId;
        this.generatedImgUrls = generatedImgUrls;
    }

    public static ValidatedGeneratedImgUrlsAddRequest from(GeneratedImgUrlsSaveRequest request) {
        return new ValidatedGeneratedImgUrlsAddRequest(
                request.getMemberId(),
                GeneratedImgUrls.from(request.getGeneratedImgUrls())
        );
    }
}
