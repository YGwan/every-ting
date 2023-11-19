package com.everyTing.photo.dto.response;

import com.everyTing.photo.domain.PhotoRequest;
import com.everyTing.photo.domain.data.PhotoRequestStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class PhotoRequestResponse {

    public Long memberId;

    public PhotoRequestStatus requestStatus;

    public LocalDateTime updatedAt;

    public PhotoRequestResponse() {
    }

    public PhotoRequestResponse(Long memberId, PhotoRequestStatus requestStatus, LocalDateTime updatedAt) {
        this.memberId = memberId;
        this.requestStatus = requestStatus;
        this.updatedAt = updatedAt;
    }

    public PhotoRequestResponse(PhotoRequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }


    public static PhotoRequestResponse from(PhotoRequest photoRequest) {
        return new PhotoRequestResponse(
                photoRequest.getMemberId(),
                photoRequest.getRequestStatus(),
                photoRequest.getUpdatedAt()
        );
    }
}
