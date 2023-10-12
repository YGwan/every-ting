package com.everyTing.team.application.service;

import com.everyTing.team.application.port.in.TeamMemberUseCase;
import com.everyTing.team.application.port.in.command.TeamMemberFindCommand;
import com.everyTing.team.application.port.out.TeamMemberPort;
import com.everyTing.team.domain.TeamMembers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TeamMemberService implements TeamMemberUseCase {

    private final TeamMemberPort teamMemberPort;

    public TeamMemberService(TeamMemberPort teamMemberPort) {
        this.teamMemberPort = teamMemberPort;
    }

    @Override
    public TeamMembers findTeamMembers(TeamMemberFindCommand command) {
        return teamMemberPort.findTeamMembers(command.getTeamId());
    }
}
