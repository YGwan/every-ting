package com.everyTing.team.application.port.out;

import com.everyTing.team.adapter.out.persistence.entity.data.Role;
import com.everyTing.team.domain.TeamMembers;

public interface TeamMemberPort {

    Boolean existsTeamMemberByTeamLeaderId(Long memberId);

    TeamMembers findTeamMembers(Long teamId);

    Long saveTeamMember(Long teamId, Long memberId, Role role);
}
