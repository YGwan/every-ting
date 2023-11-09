package com.everyTing.photo.dto.validatedRequest;

import com.everyTing.photo.domain.data.GeneratedImgUrls;
import com.everyTing.photo.dto.request.GeneratedPhotoAddRequest;
import lombok.Getter;

@Getter
public class ValidatedGeneratedPhotoAddRequest {

    private final Long memberId;

    private final GeneratedImgUrls generatedImgUrls;

    public ValidatedGeneratedPhotoAddRequest(Long memberId, GeneratedImgUrls generatedImgUrls) {
        this.memberId = memberId;
        this.generatedImgUrls = generatedImgUrls;
    }

    public static ValidatedGeneratedPhotoAddRequest from(GeneratedPhotoAddRequest request) {
        return new ValidatedGeneratedPhotoAddRequest(
                request.getMemberId(),
                GeneratedImgUrls.from(request.getGeneratedImgUrls())
        );
    }
}
