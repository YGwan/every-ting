package com.everyTing.team.application.port.in;

import com.everyTing.team.application.port.in.command.TeamFindCommand;
import com.everyTing.team.application.port.in.command.TeamSaveCommand;
import com.everyTing.team.domain.Team;
import java.util.UUID;

public interface TeamUseCase {

    Team findTeam(TeamFindCommand command);

    Long saveTeam(TeamSaveCommand command);

    default String generateCode() {
        return UUID.randomUUID()
                   .toString();
    }
}
