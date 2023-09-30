package com.everyTing.core.exception.errorCode;

import org.springframework.http.HttpStatus;

public interface ApplicationErrorCode {
    HttpStatus getStatus();
    String getErrorCode();
    String getMessage();
}
