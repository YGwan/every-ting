package com.everyTing.notification.dto.form;

public class PhotoGeneratedErrorForm implements NotificationForm {

    @Override
    public String title() {
        return "에러";
    }

    @Override
    public String body() {
        return "프로필 사진을 생성 과정에서 문제가 발생했습니다. " +
                "재로그인 시에 다시 프로필을 설정하실 수 있습니다.";
    }
}
