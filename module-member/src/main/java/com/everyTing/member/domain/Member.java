package com.everyTing.member.domain;

import com.everyTing.core.domain.Gender;
import com.everyTing.member.dto.request.SignUpRequest;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 4, max = 100)
    @Column(unique = true, nullable = false, length = 100)
    private String username;

    @NotNull
    @Column(nullable = false, length = 5)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    @Column(nullable = false)
    private LocalDate birth;

    @Email
    @NotNull
    @Column(unique = true, nullable = false, length = 100)
    private String socialEmail;

    @Email
    @NotNull
    @Column(unique = true, nullable = false, length = 100)
    private String universityEmail;

    @NotNull
    @Size(max = 70)
    @Column(length = 70, nullable = false)
    private String university;

    @NotNull
    @Size(max = 50)
    @Column(length = 50, nullable = false)
    private String major;

    @NotNull
    @Size(min = 4, max = 100)
    @Column(unique = true, nullable = false, length = 100)
    private String kakaoId;

    @NotNull
    private double height;

    @NotNull
    private double weight;

    @NotNull
    private String profilePhoto;

    public Member(String username, Gender gender, LocalDate birth, String socialEmail, String universityEmail,
                  String university, String major, String kakaoId, double height, double weight) {
        this.username = username;
        this.gender = gender;
        this.birth = birth;
        this.socialEmail = socialEmail;
        this.universityEmail = universityEmail;
        this.university = university;
        this.major = major;
        this.kakaoId = kakaoId;
        this.height = height;
        this.weight = weight;
    }

    public static Member from(SignUpRequest request) {
        return new Member(
                request.getUsername(),
                request.getGender(),
                request.getBirth(),
                request.getSocialEmail(),
                request.getUniversityEmail(),
                request.getUniversity(),
                request.getMajor(),
                request.getKakaoId(),
                request.getHeight(),
                request.getWeight()
        );
    }
}
