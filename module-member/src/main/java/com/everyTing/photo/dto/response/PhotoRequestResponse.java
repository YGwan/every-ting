package com.everyTing.photo.dto.response;

import com.everyTing.photo.domain.PhotoRequest;
import com.everyTing.photo.domain.data.PhotoRequestStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class PhotoRequestResponse {

    public PhotoRequestStatus requestStatus;

    public LocalDateTime createdAt;

    public PhotoRequestResponse() {
    }

    public PhotoRequestResponse(PhotoRequestStatus requestStatus, LocalDateTime createdAt) {
        this.requestStatus = requestStatus;
        this.createdAt = createdAt;
    }

    public PhotoRequestResponse(PhotoRequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }


    public static PhotoRequestResponse from(PhotoRequest photoRequest) {
        return new PhotoRequestResponse(
                photoRequest.getRequestStatus(),
                photoRequest.getCreatedAt()
        );
    }
}
