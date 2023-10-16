package com.everyTing.member.dto.request;

import com.everyTing.core.domain.Gender;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SignUpRequest {

    private final String username;

    private final Gender gender;

    private final LocalDate birth;

    private final String universityEmail;

    private final String password;

    private final String kakaoId;

    private final String university;

    private final String major;

    public SignUpRequest(String username, Gender gender, LocalDate birth, String universityEmail,
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
