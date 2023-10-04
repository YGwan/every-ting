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
    private String socialEmail;
    private String university;
    private String major;
    private String kakaoId;
    private String height;
    private String weight;
    private String universityEmail;
    private String profilePhoto;

    public Member(Long memberId, String username, Gender gender, LocalDate birth,
        String socialEmail,
        String university, String major, String kakaoId, String height, String weight,
        String universityEmail, String profilePhoto) {
        this.memberId = memberId;
        this.username = username;
        this.gender = gender;
        this.birth = birth;
        this.socialEmail = socialEmail;
        this.university = university;
        this.major = major;
        this.kakaoId = kakaoId;
        this.height = height;
        this.weight = weight;
        this.universityEmail = universityEmail;
        this.profilePhoto = profilePhoto;
    }
}
