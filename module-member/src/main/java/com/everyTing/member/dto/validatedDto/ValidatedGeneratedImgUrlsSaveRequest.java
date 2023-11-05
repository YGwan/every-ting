package com.everyTing.member.dto.validatedDto;

import com.everyTing.member.domain.data.GeneratedImgUrls;
import com.everyTing.member.dto.request.GeneratedImgUrlsSaveRequest;
import lombok.Getter;

@Getter
public class ValidatedGeneratedImgUrlsSaveRequest {

    private final Long userId;

    private final GeneratedImgUrls generatedImgUrls;

    public ValidatedGeneratedImgUrlsSaveRequest(Long userId, GeneratedImgUrls generatedImgUrls) {
        this.userId = userId;
        this.generatedImgUrls = generatedImgUrls;
    }

    public static ValidatedGeneratedImgUrlsSaveRequest from(GeneratedImgUrlsSaveRequest request) {
        return new ValidatedGeneratedImgUrlsSaveRequest(
                request.getUserId(),
                GeneratedImgUrls.from(request.getGeneratedImgUrls())
        );
    }
}
