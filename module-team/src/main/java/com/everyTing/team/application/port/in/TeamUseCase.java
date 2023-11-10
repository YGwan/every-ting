package com.everyTing.team.application.port.in;

import com.everyTing.team.application.port.in.command.TeamFindByCodeCommand;
import com.everyTing.team.application.port.in.command.TeamFindByIdCommand;
import com.everyTing.team.application.port.in.command.TeamRemoveCommand;
import com.everyTing.team.application.port.in.command.TeamSaveCommand;
import com.everyTing.team.domain.Team;

public interface TeamUseCase {

    Team findTeamById(TeamFindByIdCommand command);

    Team findTeamByCode(TeamFindByCodeCommand command);

    Long saveTeam(TeamSaveCommand command);

    void removeTeam(TeamRemoveCommand command);
}
