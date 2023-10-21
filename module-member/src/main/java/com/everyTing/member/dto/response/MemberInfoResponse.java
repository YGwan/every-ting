package com.everyTing.member.dto.response;

import com.everyTing.core.domain.Gender;
import com.everyTing.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberInfoResponse {

    private final Long id;

    private final String username;

    private final Gender gender;

    private final Integer birth;

    private final String universityEmail;

    private final String university;

    private final String major;

    private final String kakaoId;

    private final String profilePhoto;

    public MemberInfoResponse(Long id, String username, Gender gender, Integer birth, String universityEmail,
                              String university, String major, String kakaoId, String profilePhoto) {
        this.id = id;
        this.username = username;
        this.gender = gender;
        this.birth = birth;
        this.universityEmail = universityEmail;
        this.university = university;
        this.major = major;
        this.kakaoId = kakaoId;
        this.profilePhoto = profilePhoto;
    }

    public static MemberInfoResponse from(Member member) {
        return new MemberInfoResponse(
                member.getId(),
                member.getUsername().getValue(),
                member.getGender(),
                member.getBirth().getValue(),
                member.getUniversityEmail().getValue(),
                member.getUniversity().getValue(),
                member.getMajor().getValue(),
                member.getKakaoId().getValue(),
                member.getProfilePhoto()
        );
    }
}
