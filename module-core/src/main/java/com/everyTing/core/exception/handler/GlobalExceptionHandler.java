package com.everyTing.core.exception.handler;

import com.everyTing.core.dto.Response;
import com.everyTing.core.exception.TingAppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TingAppException.class)
    public ResponseEntity<?> handlerCustomException(TingAppException e) {
        log.error("{}: {}", e.getErrorCode(), e.getMessage());

        return ResponseEntity.status(e.getStatus())
                             .body(Response.error(e.getErrorCode(), e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerException(Exception e) {
        log.error("{}", e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .build();
    }
}
