package com.everyTing.photo.domain.data;

import lombok.Getter;

@Getter
public enum PhotoRequestStatus {
    FAILED("실패"),
    REQUESTED("요청중"),
    COMPLETED("성공"),
    NOT_FOUND("없음");

    final String status;

    PhotoRequestStatus(String status) {
        this.status = status;
    }
}
