package com.everyTing.team.application.port.in;

import com.everyTing.team.application.port.in.command.TeamRequestFindCommand;
import com.everyTing.team.application.port.in.command.TeamRequestSaveCommand;
import com.everyTing.team.domain.TeamRequests;

public interface TeamRequestUseCase {

    Long saveTeamRequest(TeamRequestSaveCommand command);

    TeamRequests findTeamRequest(TeamRequestFindCommand command);
}
