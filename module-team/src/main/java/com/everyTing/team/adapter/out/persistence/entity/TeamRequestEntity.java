package com.everyTing.team.adapter.out.persistence.entity;

import com.everyTing.core.domain.CreatedDateFields;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(name = "team_request",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"fromTeamId", "toTeamId"})
    })
@Entity
public class TeamRequestEntity extends CreatedDateFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fromTeamId;

    private Long toTeamId;

    protected TeamRequestEntity() {
    }

    private TeamRequestEntity(Long fromTeamId, Long toTeamId) {
        this.fromTeamId = fromTeamId;
        this.toTeamId = toTeamId;
    }

    public static TeamRequestEntity of(Long fromTeamId, Long toTeamId) {
        return new TeamRequestEntity(fromTeamId, toTeamId);
    }

    public Long getId() {
        return id;
    }

    public Long getFromTeamId() {
        return fromTeamId;
    }

    public Long getToTeamId() {
        return toTeamId;
    }
}
