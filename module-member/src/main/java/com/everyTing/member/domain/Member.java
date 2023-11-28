package com.everyTing.member.domain;

import com.everyTing.core.domain.AuditingFields;
import com.everyTing.core.domain.Gender;
import com.everyTing.member.domain.data.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Username username;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private BirthYear birth;

    private UniversityEmail universityEmail;

    @Embedded
    private Password password;

    private University university;

    private Major major;

    private KakaoId kakaoId;

    private ProfilePhoto profilePhoto;

    private Member(Username username, Gender gender, BirthYear birth, UniversityEmail universityEmail,
                  Password password, University university, Major major, KakaoId kakaoId, ProfilePhoto profilePhoto) {
        this.username = username;
        this.gender = gender;
        this.birth = birth;
        this.universityEmail = universityEmail;
        this.password = password;
        this.university = university;
        this.major = major;
        this.kakaoId = kakaoId;
        this.profilePhoto = profilePhoto;
    }

    public static Member of(Username username, Gender gender, BirthYear birth, UniversityEmail universityEmail,
                   Password password, University university, Major major, KakaoId kakaoId, ProfilePhoto profilePhoto) {
        return new Member(
                username,
                gender,
                birth,
                universityEmail,
                password,
                university,
                major,
                kakaoId,
                profilePhoto
        );
    }

    public void modifyUsername(Username newUsername) {
        this.username = newUsername;
    }

    public void modifyPassword(Password newPassword) {
        this.password = newPassword;
    }

    public void modifyKakaoId(KakaoId newKakaoId) {
        this.kakaoId = newKakaoId;
    }

    public void modifyProfilePhoto(ProfilePhoto profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public boolean isSamePassword(Password enterPassword) {
        return this.password.isSame(enterPassword);
    }

    public String getSalt() {
        return password.getSalt();
    }
}
