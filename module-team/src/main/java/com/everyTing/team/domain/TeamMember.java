package com.everyTing.team.domain;

import com.everyTing.team.adapter.out.persistence.entity.TeamMemberEntity;
import com.everyTing.team.adapter.out.persistence.entity.data.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
public class TeamMember {

    private final Long teamMemberId;
    private final Role role;
    private final Long teamId;
    private final Long memberId;

    private TeamMember(Long teamMemberId, Role role, Long teamId, Long memberId) {
        this.teamMemberId = teamMemberId;
        this.role = role;
        this.teamId = teamId;
        this.memberId = memberId;
    }

    public static TeamMember from(TeamMemberEntity entity) {
        return new TeamMember(entity.getId(), entity.getRole(), entity.getTeamId(), entity.getMemberId());
    }
}
