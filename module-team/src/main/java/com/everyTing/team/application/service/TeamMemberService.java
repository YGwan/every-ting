package com.everyTing.team.application.service;

import com.everyTing.core.feign.dto.Member;
import com.everyTing.team.application.port.in.TeamMemberUseCase;
import com.everyTing.team.application.port.in.command.TeamMemberFindCommand;
import com.everyTing.team.application.port.in.command.TeamMemberSaveCommand;
import com.everyTing.team.application.port.out.MemberPort;
import com.everyTing.team.application.port.out.TeamMemberPort;
import com.everyTing.team.domain.TeamMembers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TeamMemberService implements TeamMemberUseCase {

    private final TeamMemberPort teamMemberPort;
    private final MemberPort memberPort;

    public TeamMemberService(TeamMemberPort teamMemberPort, MemberPort memberPort) {
        this.teamMemberPort = teamMemberPort;
        this.memberPort = memberPort;
    }

    @Override
    public TeamMembers findTeamMembers(TeamMemberFindCommand command) {
        return teamMemberPort.findTeamMembersByTeamId(command.getTeamId());
    }

    @Override
    @Transactional
    public Long saveTeamMember(TeamMemberSaveCommand command) {
        final Member member = memberPort.getMemberById(command.getMemberId());

        return teamMemberPort.saveTeamMember(command.getTeamId(), member);
    }
}
