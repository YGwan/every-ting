package com.everyTing.notification.service.fcm.form;

import com.everyTing.notification.domain.data.Body;

public class ErrorForm implements NotificationForm {

    private final Body body;

    public ErrorForm(Body body) {
        this.body = body;
    }

    @Override
    public String title() {
        return "오류 발생";
    }

    @Override
    public String body() {
        return body.getValue();
    }
}
