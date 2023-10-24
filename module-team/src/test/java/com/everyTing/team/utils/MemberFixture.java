package com.everyTing.team.utils;

import com.everyTing.core.domain.Gender;
import com.everyTing.core.feign.dto.Member;

import java.time.LocalDate;

public class MemberFixture {

    public static Member get(Long memberId) {
        Member member = new Member(memberId, "김학생", Gender.FEMALE, 2000,
                "단국대학교", "컴퓨터공학과", "kakaoId", "stu@uni.com", "image_url.com");
        return member;
    }
}
