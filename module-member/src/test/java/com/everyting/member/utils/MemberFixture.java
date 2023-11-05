package com.everyting.member.utils;

import com.everyTing.core.domain.Gender;
import com.everyTing.member.domain.Member;
import com.everyTing.member.domain.data.*;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

public class MemberFixture {

    public static final Username username = Username.from("Ygwan");
    public static final Gender gender = Gender.MALE;
    public static final BirthYear birth = BirthYear.from(1998);
    public static final UniversityEmail universityEmail = UniversityEmail.from("32180000@dankook.ac.kr");
    public static final Password password = Password.from("qwer1234!");
    public static final University university = University.from("단국대학교");
    public static final Major major = Major.from("컴퓨터공학과");
    public static final KakaoId kakaoId = KakaoId.from("everyting");
    public static final ProfilePhoto profilePhoto = ProfilePhoto.from("profile.url");
    public static final LocalDateTime createdAt = now();
    public static final LocalDateTime updatedAt = now();

    public static Member get(Long memberId) {
        Member member = Member.of(username, gender, birth, universityEmail, password,
                university, major, kakaoId, profilePhoto);
        ReflectionTestUtils.setField(member, "id", memberId);
        ReflectionTestUtils.setField(member, "createdAt", createdAt);
        ReflectionTestUtils.setField(member, "updatedAt", updatedAt);

        return member;
    }
}
