package com.everyTing.team.application.port.in;

import com.everyTing.team.application.port.in.command.TeamRequestFindCommand;
import com.everyTing.team.application.port.in.command.TeamRequestSaveCommand;
import com.everyTing.team.application.port.in.command.TeamRequestsFindCommand;
import com.everyTing.team.domain.TeamRequest;
import com.everyTing.team.domain.TeamRequests;

public interface TeamRequestUseCase {

    TeamRequest findTeamRequest(TeamRequestFindCommand command);

    TeamRequests findTeamRequests(TeamRequestsFindCommand command);

    Long saveTeamRequest(TeamRequestSaveCommand command);
}
