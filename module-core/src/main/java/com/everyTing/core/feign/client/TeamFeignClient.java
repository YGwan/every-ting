package com.everyTing.core.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "${service.team.name}", url = "${service.team.url}")
public interface TeamFeignClient {

}
