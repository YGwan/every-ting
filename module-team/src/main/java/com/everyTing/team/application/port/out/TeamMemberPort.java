package com.everyTing.team.application.port.out;

import com.everyTing.core.feign.dto.Member;
import com.everyTing.team.adapter.out.persistence.entity.data.Role;
import com.everyTing.team.domain.TeamMember;
import com.everyTing.team.domain.TeamMembers;

public interface TeamMemberPort {

    Boolean existsTeamMemberByTeamIdAndTeamMemberId(Long teamId, Long teamMemberId);

    Boolean existsTeamLeaderByMemberId(Long memberId);

    Boolean existsTeamLeaderByTeamIdAndMemberId(Long teamId, Long memberId);

    TeamMembers findTeamMembersByTeamId(Long teamId);

    TeamMembers findTeamMembersByMemberId(Long memberId);

    TeamMembers findTeamMembersByMemberIdAndRole(Long memberId, Role role);

    TeamMember findTeamMemberByTeamIdAndMemberId(Long teamId, Long memberId);

    TeamMember findTeamLeader(Long teamId);

    void removeTeamMember(Long teamId, Long teamMemberId);

    Long saveTeamLeader(Long teamId, Long memberId);

    Long saveTeamMember(Long teamId, Member member);
}
