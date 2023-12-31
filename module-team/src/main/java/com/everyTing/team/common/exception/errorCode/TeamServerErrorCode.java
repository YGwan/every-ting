package com.everyTing.team.common.exception.errorCode;

import com.everyTing.core.exception.errorCode.ServerErrorCode;

public enum TeamServerErrorCode implements ServerErrorCode {

    TSER_001("전공이 빈 값입니다."),
    TSER_002("팀 초대코드가 빈 값입니다."),
    TSER_003("대학교가 빈 값입니다."),
    TSER_004("팀을 생성하려는 멤버의 정보를 받아오는 과정에서 에러가 발생했습니다."),
    TSER_005("팀을 생성하려는 멤버의 id로 멤버를 조회할 수 없습니다."),
    TSER_006("미팅 매칭을 위해 redis lock 을 획득하던 중에 interrupt exception이 발생했습니다."),
    TSER_007("미팅 매칭을 위한 redis lock 을 획득하는 것에 실패했습니다."),
    TSER_008("팀 코드를 생성하는 데 실패했습니다."),
    TSER_009("미팅 요청을 위한 redis lock 을 획득하는 것에 실패했습니다."),
    TSER_010("미팅 요청을 위해 redis lock 을 획득하던 중에 interrupt exception이 발생했습니다."),
    ;

    private String message;

    TeamServerErrorCode(String message) {
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
