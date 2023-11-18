package com.everyTing.notification.dto.form;

import com.everyTing.notification.domain.constant.NotificationType;

public class PhotoGeneratedCompletedForm implements NotificationForm {

    @Override
    public String title() {
        return "성공";
    }

    @Override
    public String body() {
        return "프로필 사진을 생성이 완료되었습니다. " +
                "재로그인 하시면 프로필 설정이 가능합니다.";
    }

    @Override
    public NotificationType notificationType() {
        return NotificationType.PROFILE_PHOTO_GENERATION_COMPLETED;
    }
}
