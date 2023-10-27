package com.everyTing.team.adapter.out.persistence.entity;

import com.everyTing.core.domain.AuditingFields;
import com.everyTing.core.domain.Gender;
import com.everyTing.team.adapter.out.persistence.entity.data.Code;
import com.everyTing.team.adapter.out.persistence.entity.data.Major;
import com.everyTing.team.adapter.out.persistence.entity.data.MemberLimit;
import com.everyTing.team.adapter.out.persistence.entity.data.Name;
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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "team")
@Entity
public class TeamEntity extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Name name;
    
    private University university;

    private Major major;

    private Code code;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private MemberLimit memberLimit;

    private Short memberNumber = 1;

    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
    List<TeamHashtagEntity> hashtags = new ArrayList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
    List<TeamRegionEntity> regions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "fromTeamId")
    List<TeamRequestEntity> sentRequests = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "teamId")
    List<TeamMemberEntity> members = new ArrayList<>();

    protected TeamEntity() {
    }

    private TeamEntity(Name name, University university, Major major, Code code,
        MemberLimit memberLimit, Gender gender) {
        this.name = name;
        this.university = university;
        this.major = major;
        this.code = code;
        this.memberLimit = memberLimit;
        this.gender = gender;
    }

    public static TeamEntity of(Name name, University university, Major major,
        Code code, MemberLimit memberLimit, Gender gender) {
        return new TeamEntity(name, university, major, code, memberLimit, gender);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name.getValue();
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

    public Short getMemberNumber() {
        return memberNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public boolean isFull() {
        return memberNumber >= getMemberLimit();
    }

    public void increaseMemberNumber() {
        this.memberNumber++;
    }

    public void decreaseMemberNumber() {
        this.memberNumber--;
    }
}
