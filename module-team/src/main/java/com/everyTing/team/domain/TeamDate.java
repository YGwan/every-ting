package com.everyTing.team.domain;

import com.everyTing.core.domain.CreatedDateFields;
import com.everyTing.team.adapter.out.persistence.entity.TeamDateEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
public class TeamDate extends CreatedDateFields {

    private final Long id;
    private final Long myTeamId;
    private final Long otherGenderTeamId;

    private TeamDate(Long id, Long myTeamId, Long otherGenderTeamId, LocalDateTime createdAt) {
        this.id = id;
        this.myTeamId = myTeamId;
        this.otherGenderTeamId = otherGenderTeamId;
        this.createdAt = createdAt;
    }

    public static TeamDate from(TeamDateEntity entity, Long myTeamId) {
        Long otherGenderTeamId =
            entity.getWomenTeamId() == myTeamId ? entity.getMenTeamId() : entity.getWomenTeamId();

        return new TeamDate(
            entity.getId(),
            myTeamId,
            otherGenderTeamId,
            entity.getCreatedAt()
        );
    }
}
