package com.everyTing.photo.errorCode;

import com.everyTing.core.exception.errorCode.ServerErrorCode;

public enum PhotoServerErrorCode implements ServerErrorCode {
    PSER_001("AWS S3 통신이 원활하지 않습니다."),
    PSER_002("이전 사진 생성 정보가 존재합니다."),
    ;

    private String message;

    PhotoServerErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getErrorCode() {
        return this.name();
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
