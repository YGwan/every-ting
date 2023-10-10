package com.everyTing.team.adapter.out.persistence.entity;

import com.everyTing.core.domain.AuditingFields;
import com.everyTing.core.domain.Gender;
import com.everyTing.team.adapter.out.persistence.entity.data.Code;
import com.everyTing.team.adapter.out.persistence.entity.data.Major;
import com.everyTing.team.adapter.out.persistence.entity.data.MemberLimit;
import com.everyTing.team.adapter.out.persistence.entity.data.Name;
import com.everyTing.team.adapter.out.persistence.entity.data.Region;
import com.everyTing.team.adapter.out.persistence.entity.data.University;
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

    private Name name;

    private Region region;

    private University university;

    private Major major;

    private Code code;

    private MemberLimit memberLimit;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
    List<TeamHashtagEntity> hashtags = new ArrayList<>();

    protected TeamEntity() {
    }

    private TeamEntity(Name name, Region region, University university, Major major, Code code,
        MemberLimit memberLimit, Gender gender) {
        this.name = name;
        this.region = region;
        this.university = university;
        this.major = major;
        this.code = code;
        this.memberLimit = memberLimit;
        this.gender = gender;
    }

    public static TeamEntity of(Name name, Region region, University university, Major major,
        Code code, MemberLimit memberLimit, Gender gender) {
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