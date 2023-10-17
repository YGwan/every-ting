package com.everyTing.team.application.port.in;

import com.everyTing.team.application.port.in.command.TeamRequestSaveCommand;

public interface TeamRequestUseCase {

    Long saveTeamRequest(TeamRequestSaveCommand command);
}
