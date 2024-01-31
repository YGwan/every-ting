package com.everyTing.member.errorCode;

import com.everyTing.core.exception.errorCode.ServerErrorCode;

public enum MemberServerErrorCode implements ServerErrorCode {
    MSER_001("패스워드 암호화할 수 있는 digest를 만드는데 문제가 발생했습니다."),
    MSER_002("멤버 데이터를 암/복호화 할수 있는 cipher를 만드는데 문제가 발생했습니다."),
    MSER_003("멤버 데이터 암호화하는 과정에서 문제가 발생했습니다."),
    MSER_004("멤버 데이터 복호화 과정에서 문제가 발생했습니다."),
    MSER_005("메일을 보내는 과정에서 문제가 발생했습니다."),
    MSER_006("멤버 Cache Warming 과정에서 문제가 발생했습니다.")
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
