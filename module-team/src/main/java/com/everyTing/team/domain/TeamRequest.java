package com.everyTing.team.domain;

import com.everyTing.core.domain.CreatedDateFields;
import com.everyTing.team.adapter.out.persistence.entity.TeamRequestEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
public class TeamRequest extends CreatedDateFields {

    private final Long id;
    private final Long fromTeamId;
    private final Long toTeamId;

    private TeamRequest(Long id, Long fromTeamId, Long toTeamId, LocalDateTime createdAt) {
        this.id = id;
        this.fromTeamId = fromTeamId;
        this.toTeamId = toTeamId;
        this.createdAt = createdAt;
    }

    public static TeamRequest from(TeamRequestEntity entity) {
        return new TeamRequest(
            entity.getId(),
            entity.getFromTeam().getId(),
            entity.getToTeam().getId(),
            entity.getCreatedAt());
    }
}
