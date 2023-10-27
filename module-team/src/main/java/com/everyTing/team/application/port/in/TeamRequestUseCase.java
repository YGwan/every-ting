package com.everyTing.team.application.port.in;

import com.everyTing.team.application.port.in.command.TeamRequestFindCommand;
import com.everyTing.team.application.port.in.command.TeamRequestSaveCommand;
import com.everyTing.team.domain.TeamRequests;

public interface TeamRequestUseCase {

    TeamRequests findTeamRequest(TeamRequestFindCommand command);

    Long saveTeamRequest(TeamRequestSaveCommand command);
}
