package com.everyTing.team.application.port.in;

import com.everyTing.team.application.port.in.command.TeamMemberSaveCommand;
import com.everyTing.team.application.port.in.command.TeamMemberFindCommand;
import com.everyTing.team.domain.TeamMembers;

public interface TeamMemberUseCase {

    TeamMembers findTeamMembers(TeamMemberFindCommand command);

    void saveTeamMember(TeamMemberSaveCommand command);
}