package com.everyTing.core.exception.errorCode;

import org.springframework.http.HttpStatus;

public enum CoreErrorCode implements ApplicationErrorCode {

    ;

    private String message;
    private HttpStatus status;

    CoreErrorCode(String message, HttpStatus status) {
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
