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

@Table(name = "team_like",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"from_team_member_id", "to_team_id"})
    })
@Entity
public class TeamLikeEntity extends CreatedDateFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "from_team_member_id")
    private TeamMemberEntity fromTeamMember;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "from_team")
    private TeamEntity fromTeam;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "to_team_id")
    private TeamEntity toTeam;

    protected TeamLikeEntity() {
    }

    private TeamLikeEntity(TeamMemberEntity fromTeamMember, TeamEntity fromTeam, TeamEntity toTeam) {
        this.fromTeamMember = fromTeamMember;
        this.fromTeam = fromTeam;
        this.toTeam = toTeam;
    }

    public static TeamLikeEntity of(TeamMemberEntity fromTeamMember, TeamEntity fromTeam, TeamEntity toTeam) {
        return new TeamLikeEntity(fromTeamMember, fromTeam, toTeam);
    }

    public Long getId() {
        return id;
    }

    public TeamMemberEntity getFromTeamMember() {
        return fromTeamMember;
    }

    public TeamEntity getFromTeam() {
        return fromTeam;
    }

    public TeamEntity getToTeam() {
        return toTeam;
    }
}
