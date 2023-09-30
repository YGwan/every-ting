package com.everyTing.core.exception.errorCode;

public enum CoreServerErrorCode implements ServerErrorCode {

    CSER_001("TingApplicationException 생성자 에러 발생"),
    CSER_002("TingServerException 생성자 에러 발생"),
    ;

    private String message;

    CoreServerErrorCode(String message) {
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
