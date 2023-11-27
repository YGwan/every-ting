package com.everyTing.team.adapter.out.service;

import static com.everyTing.team.common.exception.errorCode.TeamServerErrorCode.TSER_004;

import com.everyTing.core.exception.TingServerException;
import com.everyTing.core.feign.client.NotificationFeignClient;
import com.everyTing.core.feign.dto.NotificationAddRequest;
import com.everyTing.team.application.port.out.NotificationPort;
import com.everyTing.team.common.notificationForm.NotificationForm;
import feign.FeignException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class NotificationServiceAdapter implements NotificationPort {

    private final NotificationFeignClient notificationFeignClient;

    public NotificationServiceAdapter(NotificationFeignClient notificationFeignClient) {
        this.notificationFeignClient = notificationFeignClient;
    }

    @Override
    public void sendNotification(Long memberId, NotificationForm notificationForm) {
        sendNotification(List.of(memberId), notificationForm);
    }

    @Override
    public void sendNotification(List<Long> memberIds, NotificationForm notificationForm) {
        for (Long memberId: memberIds) {
            final NotificationAddRequest request = NotificationAddRequest.of(memberId,
                notificationForm.getTitle(), notificationForm.getBody(),
                notificationForm.getTargetId(), notificationForm.getNotificationType());

            try {
                notificationFeignClient.notificationAdd(request);
            } catch (FeignException e) {
                throw new TingServerException(TSER_004);
            }
        }
    }
}
