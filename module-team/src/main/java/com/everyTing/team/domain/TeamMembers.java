package com.everyTing.team.domain;

import com.everyTing.team.adapter.out.persistence.entity.TeamMemberEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
public class TeamMembers {

    private final List<TeamMember> teamMembers;

    private TeamMembers(List<TeamMember> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public static TeamMembers from(List<TeamMemberEntity> teamHashtagEntities) {
        return new TeamMembers(teamHashtagEntities.stream()
                                                  .map(TeamMember::from)
                                                  .collect(Collectors.toUnmodifiableList()));
    }
}
