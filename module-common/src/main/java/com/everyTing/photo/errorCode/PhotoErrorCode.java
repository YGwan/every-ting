package com.everyTing.photo.errorCode;

import com.everyTing.core.exception.errorCode.ApplicationErrorCode;
import org.springframework.http.HttpStatus;

public enum PhotoErrorCode implements ApplicationErrorCode {
    PHOTO_001("생성된 프로필 사진 갯수가 올바르지 않습니다."),
    PHOTO_002("아직 완료되지 않은 프로필 생성 작업이 존재합니다."),
    PHOTO_003("생성된 프로필 사진 링크가 올바르지 않습니다."),
    PHOTO_004("생성된 프로필 사진을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    PHOTO_005("프로필 생성 요청 정보가 이미 존재합니다."),
    PHOTO_006("프로필 생성 요청 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    PHOTO_007("이전 프로필 사진 생성 정보가 존재합니다."),
    ;


    private final String message;
    private final HttpStatus status;

    PhotoErrorCode(String message) {
        this.message = message;
        this.status = HttpStatus.BAD_REQUEST;
    }

    PhotoErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String getErrorCode() {
        return this.name();
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public HttpStatus getStatus() {
        return this.status;
    }
}
