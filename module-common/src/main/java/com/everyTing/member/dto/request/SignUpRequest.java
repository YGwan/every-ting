package com.everyTing.member.dto.request;

import com.everyTing.core.domain.Gender;
import com.everyTing.member.domain.Member;
import com.everyTing.member.domain.data.*;
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

    private String profilePhoto;

    public SignUpRequest() {
    }

    public SignUpRequest(String username, Gender gender, Integer birth, String universityEmail, String password,
                         String kakaoId, String university, String major, String profilePhoto) {
        this.username = username;
        this.gender = gender;
        this.birth = birth;
        this.universityEmail = universityEmail;
        this.password = password;
        this.kakaoId = kakaoId;
        this.university = university;
        this.major = major;
        this.profilePhoto = profilePhoto;
    }

    public Username usernameEntity() {
        return Username.from(username);
    }

    public UniversityEmail universityEmailEntity() {
        return UniversityEmail.from(universityEmail);
    }

    public KakaoId kakaoIdEntity() {
        return KakaoId.from(kakaoId);
    }

    public Member withPassword(Password password) {
        return Member.of(
                Username.from(username),
                gender,
                BirthYear.from(birth),
                UniversityEmail.from(universityEmail),
                password,
                University.from(university),
                Major.from(major),
                KakaoId.from(kakaoId),
                ProfilePhoto.from(profilePhoto)
        );
    }
}
