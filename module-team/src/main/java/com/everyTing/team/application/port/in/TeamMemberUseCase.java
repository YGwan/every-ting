package com.everyTing.team.application.port.in;

import com.everyTing.team.application.port.in.command.TeamMemberFindCommand;
import com.everyTing.team.domain.TeamMembers;

public interface TeamMemberUseCase {

    TeamMembers findMembers(TeamMemberFindCommand command);
}