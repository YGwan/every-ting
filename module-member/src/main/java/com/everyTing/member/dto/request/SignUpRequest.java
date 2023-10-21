package com.everyTing.member.dto.request;

import com.everyTing.core.domain.Gender;
import com.everyTing.member.domain.data.BirthYear;
import lombok.Getter;

@Getter
public class SignUpRequest {

    private String username;

    private Gender gender;

    private Integer birth;

    private String universityEmail;

    private String password;

    private String kakaoId;

    private String university;

    private String major;

    public SignUpRequest() {
    }

    public SignUpRequest(String username, Gender gender, Integer birth, String universityEmail,
                         String password, String kakaoId, String university, String major) {
        this.username = username;
        this.gender = gender;
        this.birth = birth;
        this.universityEmail = universityEmail;
        this.password = password;
        this.kakaoId = kakaoId;
        this.university = university;
        this.major = major;
    }
}
