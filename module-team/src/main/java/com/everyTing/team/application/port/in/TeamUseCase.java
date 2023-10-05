package com.everyTing.team.application.port.in;

import com.everyTing.team.application.port.in.command.TeamSaveCommand;
import java.util.UUID;

public interface TeamUseCase {

    Long saveTeam(TeamSaveCommand command);

    default String generateCode() {
        return UUID.randomUUID()
                   .toString();
    }
}
