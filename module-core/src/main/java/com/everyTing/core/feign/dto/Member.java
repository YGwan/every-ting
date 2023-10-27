package com.everyTing.core.feign.dto;

import com.everyTing.core.domain.Gender;
import lombok.Getter;

@Getter
public class Member {

    private Long id;
    private String username;
    private Gender gender;
    private Integer birth;
    private String universityEmail;
    private String university;
    private String major;
    private String kakaoId;
    private String profilePhoto;

    public Member() {
    }

    public Member(Long id, String username, Gender gender, Integer birth, String universityEmail,
        String university, String major, String kakaoId, String profilePhoto) {
        this.id = id;
        this.username = username;
        this.gender = gender;
        this.birth = birth;
        this.universityEmail = universityEmail;
        this.university = university;
        this.major = major;
        this.kakaoId = kakaoId;
        this.profilePhoto = profilePhoto;
    }
}
