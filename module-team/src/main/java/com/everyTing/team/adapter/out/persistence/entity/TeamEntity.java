package com.everyTing.team.adapter.out.persistence.entity;

import com.everyTing.core.domain.AuditingFields;
import com.everyTing.core.domain.Gender;
import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "team")
@Entity
public class TeamEntity extends AuditingFields {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(length = 20)
    private String name;

    @NotNull
    private String region;

    @NotNull
    private String university;

    @NotNull
    private String major;

    @NotNull
    private String code;

    @NotNull
    private Short memberLimit;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    protected TeamEntity() {
    }

    private TeamEntity(String name, String region, String university, String major, String code,
        Short memberLimit, Gender gender) {
        this.name = name;
        this.region = region;
        this.university = university;
        this.major = major;
        this.code = code;
        this.memberLimit = memberLimit;
        this.gender = gender;
    }
}
