package com.everyTing.member.dto.request;

import com.everyTing.core.domain.Gender;
import com.everyTing.member.domain.data.*;
import lombok.Getter;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
public class SignUpRequest {

    private String username;

    private Gender gender;

    private LocalDate birth;

    private String universityEmail;

    private String password;

    private String  kakaoId;

    private double height;

    private double weight;

    private String university;

    private String major;

    public SignUpRequest() {
    }

    public SignUpRequest(String username, Gender gender, LocalDate birth, String universityEmail, String password,
                         String kakaoId, double height, double weight, String university, String major) {
        this.username = username;
        this.gender = gender;
        this.birth = birth;
        this.universityEmail = universityEmail;
        this.password = password;
        this.kakaoId = kakaoId;
        this.height = height;
        this.weight = weight;
        this.university = university;
        this.major = major;
    }
}
