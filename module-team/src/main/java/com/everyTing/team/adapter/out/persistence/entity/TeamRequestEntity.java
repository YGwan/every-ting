package com.everyTing.team.adapter.out.persistence.entity;

import com.everyTing.core.domain.CreatedDateFields;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(name = "team_request",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"from_team_id", "to_team_id"})
    })
@Entity
public class TeamRequestEntity extends CreatedDateFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "from_team_id")
    private TeamEntity fromTeam;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "to_team_id")
    private TeamEntity toTeam;

    protected TeamRequestEntity() {
    }

    private TeamRequestEntity(TeamEntity fromTeam, TeamEntity toTeam) {
        this.fromTeam = fromTeam;
        this.toTeam = toTeam;
    }

    public static TeamRequestEntity of(TeamEntity fromTeam, TeamEntity toTeam) {
        return new TeamRequestEntity(fromTeam, toTeam);
    }

    public Long getId() {
        return id;
    }

    public TeamEntity getFromTeam() {
        return fromTeam;
    }

    public TeamEntity getToTeam() {
        return toTeam;
    }
}
