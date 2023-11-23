package com.everyTing.member.errorCode;

import com.everyTing.core.exception.errorCode.ServerErrorCode;

public enum MemberServerErrorCode implements ServerErrorCode {
    MSER_001("패스워드 암호화하는 과정에서 문제가 발생했습니다."),
    MSER_002("멤버 데이터 암호화하는 과정에서 문제가 발생했습니다."),
    MSER_003("멤버 데이터 복호화 과정에서 문제가 발생했습니다."),
    ;

    private String message;

    MemberServerErrorCode(String message) {
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
