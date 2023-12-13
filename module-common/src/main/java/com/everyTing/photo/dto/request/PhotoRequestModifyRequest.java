package com.everyTing.photo.dto.request;

import com.everyTing.photo.domain.data.PhotoRequestStatus;
import lombok.Getter;

@Getter
public class PhotoRequestModifyRequest {

    private Long memberId;

    private PhotoRequestStatus status;

    public PhotoRequestModifyRequest() {
    }

    public PhotoRequestModifyRequest(Long memberId, PhotoRequestStatus status) {
        this.memberId = memberId;
        this.status = status;
    }
}
