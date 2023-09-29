package com.everyTing.core.exception;

import com.everyTing.core.exception.errorCode.BaseErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonInclude(Include.NON_NULL)
public class TingAppException extends RuntimeException {

    private HttpStatus status;
    private String errorCode;
    private String message;

    public TingAppException(Enum<?> errorCode) {
        if (errorCode instanceof BaseErrorCode) {
            BaseErrorCode baseErrorCode = (BaseErrorCode) errorCode;

            this.status = baseErrorCode.getStatus();
            this.errorCode = baseErrorCode.getErrorCode();
            this.message = baseErrorCode.getMessage();
        } else {
            this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
