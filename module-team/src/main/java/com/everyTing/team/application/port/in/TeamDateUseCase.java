package com.everyTing.team.application.port.in;

import com.everyTing.team.application.port.in.command.TeamDateCountCommand;
import com.everyTing.team.application.port.in.command.TeamDateSaveCommand;

public interface TeamDateUseCase {

    Long countRemainingTeamDate(TeamDateCountCommand command);

    Long saveTeamDate(TeamDateSaveCommand command);
}
