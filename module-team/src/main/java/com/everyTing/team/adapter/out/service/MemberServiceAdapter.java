package com.everyTing.team.adapter.out.service;

import static com.everyTing.team.common.exception.errorCode.TeamServerErrorCode.TSER_004;
import static com.everyTing.team.common.exception.errorCode.TeamServerErrorCode.TSER_005;

import com.everyTing.core.exception.TingServerException;
import com.everyTing.core.feign.client.MemberFeignClient;
import com.everyTing.core.feign.dto.Member;
import com.everyTing.team.application.port.out.MemberPort;
import feign.FeignException;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceAdapter implements MemberPort {

    private final MemberFeignClient memberFeignClient;

    public MemberServiceAdapter(MemberFeignClient memberFeignClient) {
        this.memberFeignClient = memberFeignClient;
    }

    @Override
    public Member getMemberById(Long memberId) {
        try {
            return memberFeignClient.findMember(memberId)
                                    .getData();
        } catch (FeignException e) {
            if (e.status() == HttpStatus.SC_NOT_FOUND) {
                throw new TingServerException(TSER_005);
            } else {
                e.printStackTrace();
                throw new TingServerException(TSER_004);
            }
        }
    }
}
