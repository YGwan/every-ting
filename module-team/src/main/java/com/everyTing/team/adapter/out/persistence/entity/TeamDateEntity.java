package com.everyTing.team.adapter.out.persistence.entity;

import com.everyTing.core.domain.AuditingFields;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(name = "team_date",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"women_team_id", "men_team_id"})
    })
@Entity
public class TeamDateEntity extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "women_team_id")
    private TeamEntity womenTeam;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "men_team_id")
    private TeamEntity menTeam;

    protected TeamDateEntity() {
    }

    private TeamDateEntity(TeamEntity womenTeam, TeamEntity menTeam) {
        this.womenTeam = womenTeam;
        this.menTeam = menTeam;
    }

    public static TeamDateEntity of(TeamEntity womenTeam, TeamEntity menTeam) {
        return new TeamDateEntity(womenTeam, menTeam);
    }

    public Long getId() {
        return id;
    }
}
