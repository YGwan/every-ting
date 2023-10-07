package com.everyTing.team.domain;

import com.everyTing.core.domain.Gender;
import com.everyTing.team.adapter.out.persistence.entity.TeamEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
public class Team {

    private final Long id;
    private final String name;
    private final String region;
    private final String university;
    private final String major;
    private final String code;
    private final Short memberLimit;
    private final Gender gender;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private Team(Long id, String name, String region, String university, String major, String code,
        Short memberLimit, Gender gender, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.university = university;
        this.major = major;
        this.code = code;
        this.memberLimit = memberLimit;
        this.gender = gender;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Team from(TeamEntity teamEntity) {
        return new Team(teamEntity.getId(), teamEntity.getName(), teamEntity.getRegion(),
            teamEntity.getUniversity(), teamEntity.getMajor(), teamEntity.getCode(),
            teamEntity.getMemberLimit(), teamEntity.getGender(), teamEntity.getCreatedAt(),
            teamEntity.getUpdatedAt());
    }
}
