package com.everyTing.member.dto.validatedDto;

import com.everyTing.core.domain.Gender;
import com.everyTing.member.domain.data.*;
import com.everyTing.member.dto.request.SignUpRequest;
import lombok.Getter;

@Getter
public class ValidatedSignUpRequest {

    private final Username username;

    private final Gender gender;

    private final BirthYear birth;

    private final UniversityEmail universityEmail;

    private final Password password;

    private final KakaoId kakaoId;

    private final University university;

    private final Major major;

    private final ProfilePhoto profilePhoto;

    public ValidatedSignUpRequest(Username username, Gender gender, BirthYear birth, UniversityEmail universityEmail,
                                  Password password, KakaoId kakaoId, University university, Major major, ProfilePhoto profilePhoto) {
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

    public static ValidatedSignUpRequest from(SignUpRequest request) {
        return new ValidatedSignUpRequest(
                Username.from(request.getUsername()),
                request.getGender(),
                BirthYear.from(request.getBirth()),
                UniversityEmail.from(request.getUniversityEmail()),
                Password.from(request.getPassword()),
                KakaoId.from(request.getKakaoId()),
                University.from(request.getUniversity()),
                Major.from(request.getMajor()),
                ProfilePhoto.from(request.getProfilePhoto())
        );
    }
}
