package com.everyTing.member.dto.request;

import com.everyTing.core.domain.Gender;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SignUpRequest {

    private String username;

    private Gender gender;

    private LocalDate birth;

    private String socialEmail;

    private String universityEmail;

    private String kakaoId;

    private double height;

    private double weight;

    private String university;

    private String major;

    public SignUpRequest() {
    }

    public SignUpRequest(String username, Gender gender, LocalDate birth, String socialEmail, String universityEmail,
                         String kakaoId, double height, double weight, String university, String major) {
        this.username = username;
        this.gender = gender;
        this.birth = birth;
        this.socialEmail = socialEmail;
        this.universityEmail = universityEmail;
        this.kakaoId = kakaoId;
        this.height = height;
        this.weight = weight;
        this.university = university;
        this.major = major;
    }
}
