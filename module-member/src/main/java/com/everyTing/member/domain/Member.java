package com.everyTing.member.domain;

import com.everyTing.core.domain.Gender;
import com.everyTing.member.domain.data.*;
import com.everyTing.member.dto.request.SignUpRequest;
import com.everyTing.member.dto.validatedDto.ValidatedSignUpRequest;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Username username;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    private LocalDate birth;

    private UniversityEmail universityEmail;

    private Password password;

    private University university;

    private Major major;

    private KakaoId kakaoId;

    private Height height;

    private Weight weight;

    private String profilePhoto;

    public Member() {
    }

    public Member(Username username, Gender gender, LocalDate birth, UniversityEmail universityEmail,
                  Password password, University university, Major major, KakaoId kakaoId, Height height, Weight weight) {
        this.username = username;
        this.gender = gender;
        this.birth = birth;
        this.universityEmail = universityEmail;
        this.password = password;
        this.university = university;
        this.major = major;
        this.kakaoId = kakaoId;
        this.height = height;
        this.weight = weight;
    }

    public static Member from(ValidatedSignUpRequest request) {
        return new Member(
                request.getUsername(),
                request.getGender(),
                request.getBirth(),
                request.getUniversityEmail(),
                request.getPassword(),
                request.getUniversity(),
                request.getMajor(),
                request.getKakaoId(),
                request.getHeight(),
                request.getWeight()
        );
    }
}
