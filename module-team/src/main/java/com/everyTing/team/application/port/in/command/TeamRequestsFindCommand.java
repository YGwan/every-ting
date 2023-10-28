package com.everyTing.team.application.port.in.command;

import lombok.Getter;

@Getter
public class TeamRequestsFindCommand {

    private final Long fromTeamId;
    private final Long toTeamId;

    private TeamRequestsFindCommand(Long fromTeamId, Long toTeamId) {
        this.fromTeamId = fromTeamId;
        this.toTeamId = toTeamId;
    }

    public static TeamRequestsFindCommand of(Long fromTeamId, Long toTeamId) {
        return new TeamRequestsFindCommand(fromTeamId, toTeamId);
    }
}
