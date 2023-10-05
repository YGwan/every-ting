package com.everyTing.team.application.port.out;

import com.everyTing.team.adapter.out.persistence.entity.data.Role;

public interface TeamMemberPort {

    Boolean existsTeamMemberByTeamLeaderId(Long memberId);

    Long saveTeamMember(Long teamId, Long memberId, Role role);
}
