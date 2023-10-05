package com.everyTing.core.feign.client;

import com.everyTing.core.feign.dto.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${service.member.name}", url = "${service.member.url}")
public interface MemberFeignClient {

    @GetMapping("/api/v1/members/{memberId}")
    Member findMember(@PathVariable("memberId") Long memberId);
}
