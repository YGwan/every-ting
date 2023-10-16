package com.everyTing.team.adapter.out.service;

import static com.everyTing.team.common.exception.errorCode.TeamServerErrorCode.TSER_004;
import static com.everyTing.team.common.exception.errorCode.TeamServerErrorCode.TSER_005;

import com.everyTing.core.domain.Gender;
import com.everyTing.core.exception.TingServerException;
import com.everyTing.core.feign.client.MemberFeignClient;
import com.everyTing.core.feign.dto.Member;
import com.everyTing.team.application.port.out.MemberPort;
import feign.FeignException;
import java.time.LocalDate;
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
//            return memberFeignClient.findMember(memberId);
            // TODO: 실제 받아온 값으로 수정
            return new Member(memberId, "김학생", Gender.FEMALE, LocalDate.now(),
                "단국대학교", "컴퓨터공학과", "kakaoId", null, null, "stu@uni.com", "image_url.com");
        } catch (FeignException e) {
            if (e.status() == HttpStatus.SC_NOT_FOUND) {
                throw new TingServerException(TSER_005);
            } else {
                throw new TingServerException(TSER_004);
            }
        }
    }
}
