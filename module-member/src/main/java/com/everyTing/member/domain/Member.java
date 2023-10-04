package com.everyTing.member.domain;

import com.everyTing.core.domain.Gender;
import com.everyTing.member.domain.data.*;
import com.everyTing.member.dto.request.SignUpRequest;
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

    public Member(String username, Gender gender, LocalDate birth, String universityEmail, String password,
                  String university, String major, String kakaoId, double height, double weight) {
        this.username = Username.from(username);
        this.gender = gender;
        this.birth = birth;
        this.universityEmail = UniversityEmail.from(universityEmail);
        this.password = Password.from(password);
        this.university = University.from(university);
        this.major = Major.from(major);
        this.kakaoId = KakaoId.from(kakaoId);
        this.height = Height.from(height);
        this.weight = Weight.from(weight);
    }

    public static Member from(SignUpRequest request) {
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
