package com.everyTing.team.adapter.out.persistence.entity;

import com.everyTing.core.domain.AuditingFields;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Table(name = "team_date",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"womenTeamId", "menTeamId"})
    })
@Entity
public class TeamDateEntity extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long womenTeamId;

    @NotNull
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
}
