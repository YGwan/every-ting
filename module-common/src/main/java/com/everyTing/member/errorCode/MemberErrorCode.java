package com.everyTing.member.errorCode;

import com.everyTing.core.exception.errorCode.ApplicationErrorCode;
import org.springframework.http.HttpStatus;

public enum MemberErrorCode implements ApplicationErrorCode {
    MEMBER_001("유저 이름은 최대 2 ~ 8글자 사이여야 합니다."),
    MEMBER_002("대학교 이메일 값이 올바르지 않습니다."),
    MEMBER_003("대학교 값이 올바르지 않습니다."),
    MEMBER_004("전공 값이 올바르지 않습니다."),
    MEMBER_005("카카오 ID 값이 올바르지 않습니다."),
    MEMBER_006("유저 이름이 중복되었습니다."),
    MEMBER_007("대학교 이메일 값이 중복되었습니다."),
    MEMBER_008("카카오 ID 값이 중복되었습니다."),
    MEMBER_009("패스워드 형식이 올바르지 않습니다."),
    MEMBER_010("로그인 정보가 맞지 않습니다.", HttpStatus.UNAUTHORIZED),
    MEMBER_011("입력 시간이 초과되었습니다.", HttpStatus.REQUEST_TIMEOUT),
    MEMBER_012("인증번호 값이 잘못되었습니다. 다시 입력해주세요.", HttpStatus.UNAUTHORIZED),
    MEMBER_013("이메일 값을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    MEMBER_014("멤버를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    MEMBER_015("등록된 이메일이 아닙니다.", HttpStatus.NOT_FOUND),
    MEMBER_016("비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED),
    MEMBER_017("출생년도 값이 올바르지 않습니다."),
    MEMBER_018("프로필 사진 값이 올바르지 않습니다."),
    ;

    private final String message;
    private final HttpStatus status;

    MemberErrorCode(String message) {
        this.message = message;
        this.status = HttpStatus.BAD_REQUEST;
    }

    MemberErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
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
