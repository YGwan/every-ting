package com.everyTing.team.application.port.out;


import com.everyTing.team.common.notificationForm.NotificationForm;
import java.util.List;

public interface NotificationPort {

    void sendNotification(Long memberId, NotificationForm notificationForm);

    void sendNotification(List<Long> memberIds, NotificationForm notificationForm);
}
