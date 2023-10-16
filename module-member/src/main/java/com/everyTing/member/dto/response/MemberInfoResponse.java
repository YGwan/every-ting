package com.everyTing.member.dto.response;

import com.everyTing.core.domain.Gender;
import com.everyTing.member.domain.Member;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberInfoResponse {

    private Long id;

    private String username;

    private Gender gender;

    private LocalDate birth;

    private String universityEmail;

    private String university;

    private String major;

    private String kakaoId;

    private String profilePhoto;

    public MemberInfoResponse(Long id, String username, Gender gender, LocalDate birth, String universityEmail,
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
                member.getBirth(),
                member.getUniversityEmail().getValue(),
                member.getUniversity().getValue(),
                member.getMajor().getValue(),
                member.getKakaoId().getValue(),
                member.getProfilePhoto()
        );
    }
}
