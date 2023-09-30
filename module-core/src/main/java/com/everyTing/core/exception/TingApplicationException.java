package com.everyTing.core.exception;

import static com.everyTing.core.exception.errorCode.CoreServerErrorCode.CSER_001;

import com.everyTing.core.exception.errorCode.ApplicationErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonInclude(Include.NON_NULL)
public class TingApplicationException extends RuntimeException {

    private HttpStatus status;
    private String errorCode;
    private String message;

    public TingApplicationException(Enum<?> errorCode) {
        if (errorCode instanceof ApplicationErrorCode) {
            ApplicationErrorCode applicationErrorCode = (ApplicationErrorCode) errorCode;

            this.status = applicationErrorCode.getStatus();
            this.errorCode = applicationErrorCode.getErrorCode();
            this.message = applicationErrorCode.getMessage();
        } else {
            throw new TingServerException(CSER_001);
        }
    }
}
