package com.everyTing.member.domain;

import com.everyTing.core.domain.AuditingFields;
import com.everyTing.core.domain.Gender;
import com.everyTing.member.domain.data.*;
import com.everyTing.member.dto.validatedDto.ValidatedSignUpRequest;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Year;

@Getter
@Entity
public class Member extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Username username;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    private Year birth;

    private UniversityEmail universityEmail;

    private Password password;

    private University university;

    private Major major;

    private KakaoId kakaoId;

    private String profilePhoto;

    public Member() {
    }

    public Member(Username username, Gender gender, Year birth, UniversityEmail universityEmail,
                  Password password, University university, Major major, KakaoId kakaoId) {
        this.username = username;
        this.gender = gender;
        this.birth = birth;
        this.universityEmail = universityEmail;
        this.password = password;
        this.university = university;
        this.major = major;
        this.kakaoId = kakaoId;
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
                request.getKakaoId()
        );
    }

    public void modifyUsername(Username newUsername) {
        this.username = newUsername;
    }

    public void modifyPassword(Password newPassword) {
        this.password = newPassword;
    }
}
