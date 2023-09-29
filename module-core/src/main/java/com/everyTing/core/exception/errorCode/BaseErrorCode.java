package com.everyTing.core.exception.errorCode;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {
    HttpStatus getStatus();
    String getErrorCode();
    String getMessage();
}
