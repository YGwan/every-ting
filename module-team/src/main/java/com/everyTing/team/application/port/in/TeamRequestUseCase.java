package com.everyTing.team.application.port.in;

import com.everyTing.team.application.port.in.command.TeamRequestCountCommand;
import com.everyTing.team.application.port.in.command.TeamRequestFindCommand;
import com.everyTing.team.application.port.in.command.TeamRequestRemoveCommand;
import com.everyTing.team.application.port.in.command.TeamRequestSaveCommand;
import com.everyTing.team.application.port.in.command.TeamRequestsFindCommand;
import com.everyTing.team.domain.TeamRequest;
import com.everyTing.team.domain.TeamRequests;

public interface TeamRequestUseCase {

    Long countRemainingTeamRequest(TeamRequestCountCommand command);

    TeamRequest findTeamRequest(TeamRequestFindCommand command);

    TeamRequests findTeamRequests(TeamRequestsFindCommand command);

    void removeTeamRequest(TeamRequestRemoveCommand command);

    Long saveTeamRequest(TeamRequestSaveCommand command);
}
