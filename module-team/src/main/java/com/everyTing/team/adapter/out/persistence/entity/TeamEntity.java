package com.everyTing.team.adapter.out.persistence.entity;

import com.everyTing.core.domain.AuditingFields;
import com.everyTing.core.domain.Gender;
import com.everyTing.team.adapter.out.persistence.entity.embedded.Code;
import com.everyTing.team.adapter.out.persistence.entity.embedded.Major;
import com.everyTing.team.adapter.out.persistence.entity.embedded.MemberLimit;
import com.everyTing.team.adapter.out.persistence.entity.embedded.Name;
import com.everyTing.team.adapter.out.persistence.entity.embedded.Region;
import com.everyTing.team.adapter.out.persistence.entity.embedded.University;
import com.sun.istack.NotNull;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "team")
@Entity
public class TeamEntity extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Name name;

    @NotNull
    private Region region;

    @NotNull
    private University university;

    @NotNull
    private Major major;

    @NotNull
    private Code code;

    @NotNull
    private MemberLimit memberLimit;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
    List<TeamHashtagEntity> hashtags = new ArrayList<>();

    protected TeamEntity() {
    }

    private TeamEntity(String name, String region, String university, String major, String code,
        Short memberLimit, Gender gender) {
        this.name = Name.from(name);
        this.region = Region.from(region);
        this.university = University.from(university);
        this.major = Major.from(major);
        this.code = Code.from(code);
        this.memberLimit = MemberLimit.from(memberLimit);
        this.gender = gender;
    }

    public static TeamEntity from(String name, String region, String university, String major,
        String code,
        Short memberLimit, Gender gender) {
        return new TeamEntity(name, region, university, major, code, memberLimit, gender);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name.getValue();
    }

    public String getRegion() {
        return region.getValue();
    }

    public String getUniversity() {
        return university.getValue();
    }

    public String getMajor() {
        return major.getValue();
    }

    public String getCode() {
        return code.getValue();
    }

    public Short getMemberLimit() {
        return memberLimit.getValue();
    }

    public Gender getGender() {
        return gender;
    }
}
