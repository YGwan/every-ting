package com.everyTing.team.adapter.out.persistence.entity;

import com.everyTing.core.domain.CreatedDateFields;

import javax.persistence.*;

@Table(name = "team_date",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"womenTeamId", "menTeamId"})
    })
@Entity
public class TeamDateEntity extends CreatedDateFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long womenTeamId;

    private Long menTeamId;

    protected TeamDateEntity() {
    }

    private TeamDateEntity(Long womenTeamId, Long menTeamId) {
        this.womenTeamId = womenTeamId;
        this.menTeamId = menTeamId;
    }

    public static TeamDateEntity of(Long womenTeamId, Long menTeamId) {
        return new TeamDateEntity(womenTeamId, menTeamId);
    }

    public Long getId() {
        return id;
    }

    public Long getWomenTeamId() {
        return womenTeamId;
    }

    public Long getMenTeamId() {
        return menTeamId;
    }
}
