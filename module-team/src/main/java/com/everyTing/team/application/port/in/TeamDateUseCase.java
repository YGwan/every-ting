package com.everyTing.team.application.port.in;

import com.everyTing.team.application.port.in.command.TeamDateSaveCommand;

public interface TeamDateUseCase {

    Long saveTeamDate(TeamDateSaveCommand command);
}
