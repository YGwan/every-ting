package com.everyTing.team.application.port.out;

import com.everyTing.core.feign.dto.Member;
import com.everyTing.team.domain.TeamMembers;

public interface TeamMemberPort {

    Boolean existsTeamMemberByTeamLeaderId(Long memberId);

    Boolean existsTeamLeaderByTeamIdAndMemberId(Long teamId, Long memberId);

    TeamMembers findTeamMembers(Long teamId);

    Long saveTeamLeader(Long teamId, Long memberId);

    Long saveTeamMember(Long teamId, Member member);
}
