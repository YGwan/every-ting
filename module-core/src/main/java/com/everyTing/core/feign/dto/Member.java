package com.everyTing.core.feign.dto;

import com.everyTing.core.domain.Gender;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class Member {

    private Long memberId;
    private String username;
    private Gender gender;
    private LocalDate birth;
    private String university;
    private String major;
    private String kakaoId;
    private String universityEmail;
    private String profilePhoto;

    public Member(Long memberId, String username, Gender gender, LocalDate birth, String university,
        String major, String kakaoId, String universityEmail, String profilePhoto) {
        this.memberId = memberId;
        this.username = username;
        this.gender = gender;
        this.birth = birth;
        this.university = university;
        this.major = major;
        this.kakaoId = kakaoId;
        this.universityEmail = universityEmail;
        this.profilePhoto = profilePhoto;
    }
}
