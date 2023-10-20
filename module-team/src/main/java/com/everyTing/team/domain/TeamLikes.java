package com.everyTing.team.domain;

import com.everyTing.team.adapter.out.persistence.entity.TeamLikeEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamMemberEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
public class TeamLikes {

    private final List<Long> fromTeamMemberIds;

    private TeamLikes(List<Long> fromTeamMemberIds) {
        this.fromTeamMemberIds = fromTeamMemberIds;
    }

    public static TeamLikes from(List<TeamLikeEntity> likeEntities) {
        return new TeamLikes(likeEntities.stream()
                                         .map(TeamLikeEntity::getFromTeamMember)
                                         .map(TeamMemberEntity::getId)
                                         .collect(
                                             Collectors.toUnmodifiableList()));
    }
}
