package com.everyTing.core.exception;

import static com.everyTing.core.exception.errorCode.CoreServerErrorCode.CSER_002;

import com.everyTing.core.exception.errorCode.ServerErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
public class TingServerException extends RuntimeException {

    private String errorCode;
    private String message;

    public TingServerException(Enum<?> errorCode) {
        if (errorCode instanceof ServerErrorCode) {
            ServerErrorCode serverErrorCode = (ServerErrorCode) errorCode;

            this.errorCode = serverErrorCode.getErrorCode();
            this.message = serverErrorCode.getMessage();
        } else {
            throw new TingServerException(CSER_002);
        }
    }
}
