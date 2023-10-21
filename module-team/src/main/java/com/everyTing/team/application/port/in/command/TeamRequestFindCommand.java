package com.everyTing.team.application.port.in.command;

import lombok.Getter;

@Getter
public class TeamRequestFindCommand {

    private final Long fromTeamId;
    private final Long toTeamId;

    private TeamRequestFindCommand(Long fromTeamId, Long toTeamId) {
        this.fromTeamId = fromTeamId;
        this.toTeamId = toTeamId;
    }

    public static TeamRequestFindCommand of(Long fromTeamId, Long toTeamId) {
        return new TeamRequestFindCommand(fromTeamId, toTeamId);
    }
}
