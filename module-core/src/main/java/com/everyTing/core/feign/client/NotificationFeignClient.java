package com.everyTing.core.feign.client;

import com.everyTing.core.dto.Response;
import com.everyTing.core.feign.dto.Member;
import com.everyTing.core.feign.dto.NotificationAddRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${service.notification.name}", url = "${service.notification.url}")
public interface NotificationFeignClient {

    @PostMapping("/api/v1/notifications")
    Response<Void> notificationAdd(@RequestBody NotificationAddRequest request);
}
