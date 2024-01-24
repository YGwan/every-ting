package com.everyTing.core.exception.handler;

import com.everyTing.core.dto.Response;
import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.core.exception.TingServerException;
import com.everyTing.core.slack.SlackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final SlackService slackService;

    public GlobalExceptionHandler(SlackService slackService) {
        this.slackService = slackService;
    }

    @ExceptionHandler(TingApplicationException.class)
    public ResponseEntity<?> handlerCustomException(TingApplicationException e) {
        log.error("{}: {}", e.getErrorCode(), e.getMessage());
        return ResponseEntity.status(e.getStatus())
                             .body(Response.error(e.getErrorCode(), e.getMessage()));
    }

    @ExceptionHandler(TingServerException.class)
    public ResponseEntity<?> handleInternalServerException(TingServerException e) {
        slackService.send(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerException(Exception e) {
        slackService.send(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .build();
    }
}
