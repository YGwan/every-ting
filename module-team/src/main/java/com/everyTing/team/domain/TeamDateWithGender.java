package com.everyTing.team.domain;

import com.everyTing.core.domain.CreatedDateFields;
import com.everyTing.team.adapter.out.persistence.entity.TeamDateEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
public class TeamDateWithGender extends CreatedDateFields {

    private final Long id;
    private final Long womenTeamId;
    private final Long menTeamId;

    private TeamDateWithGender(Long id, Long womenTeamId, Long menTeamId, LocalDateTime createdAt) {
        this.id = id;
        this.womenTeamId = womenTeamId;
        this.menTeamId = menTeamId;
        this.createdAt = createdAt;
    }

    public static TeamDateWithGender from(TeamDateEntity entity) {
      return new TeamDateWithGender(
            entity.getId(),
            entity.getWomenTeamId(),
            entity.getMenTeamId(),
            entity.getCreatedAt()
        );
    }
}
