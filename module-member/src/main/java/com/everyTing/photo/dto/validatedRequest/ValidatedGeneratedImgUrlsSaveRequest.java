package com.everyTing.photo.dto.validatedRequest;

import com.everyTing.photo.domain.data.GeneratedImgUrls;
import com.everyTing.photo.dto.request.GeneratedImgUrlsSaveRequest;
import lombok.Getter;

@Getter
public class ValidatedGeneratedImgUrlsSaveRequest {

    private final Long memberId;

    private final GeneratedImgUrls generatedImgUrls;

    public ValidatedGeneratedImgUrlsSaveRequest(Long memberId, GeneratedImgUrls generatedImgUrls) {
        this.memberId = memberId;
        this.generatedImgUrls = generatedImgUrls;
    }

    public static ValidatedGeneratedImgUrlsSaveRequest from(GeneratedImgUrlsSaveRequest request) {
        return new ValidatedGeneratedImgUrlsSaveRequest(
                request.getMemberId(),
                GeneratedImgUrls.from(request.getGeneratedImgUrls())
        );
    }
}
