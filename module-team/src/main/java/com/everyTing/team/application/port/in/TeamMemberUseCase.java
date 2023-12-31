package com.everyTing.team.application.port.in;

import com.everyTing.team.application.port.in.command.TeamMemberRemoveCommand;
import com.everyTing.team.application.port.in.command.TeamMemberSaveCommand;
import com.everyTing.team.application.port.in.command.TeamMemberFindCommand;
import com.everyTing.team.domain.TeamMembers;

public interface TeamMemberUseCase {

    TeamMembers findTeamMembers(TeamMemberFindCommand command);

    Long saveTeamMember(TeamMemberSaveCommand command);

    void removeTeamMember(TeamMemberRemoveCommand command);
}