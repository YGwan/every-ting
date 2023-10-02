package com.everyTing.core.token.errorCode;

import com.everyTing.core.exception.errorCode.ApplicationErrorCode;
import org.springframework.http.HttpStatus;

/*
Token Error의 경우 에러 status가 UNAUTHORIZED 이다. (이후 다른 값이 필요하다면 생성자를 추가 도입)
 */
public enum TokenErrorCode implements ApplicationErrorCode {
    TOKEN_001("Is not expired token"),
    TOKEN_002("Invalid JWT token"),
    TOKEN_003("Invalid JWT signature."),
    TOKEN_004("Unsupported JWT token.")
    ;

    private final String message;
    private final HttpStatus status;

    TokenErrorCode(String message) {
        this.message = message;
        this.status = HttpStatus.UNAUTHORIZED;
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

