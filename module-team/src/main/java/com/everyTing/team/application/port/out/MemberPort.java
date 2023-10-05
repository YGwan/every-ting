package com.everyTing.team.application.port.out;


import com.everyTing.core.feign.dto.Member;

public interface MemberPort {

    Member getMemberById(Long memberId);
}
