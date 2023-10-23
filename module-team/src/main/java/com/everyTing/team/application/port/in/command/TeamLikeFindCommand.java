package com.everyTing.team.application.port.in.command;

import lombok.Getter;

@Getter
public class TeamLikeFindCommand {

    private final Long fromTeamId;
    private final Long toTeamId;

    private TeamLikeFindCommand(Long fromTeamId, Long toTeamId) {
        this.fromTeamId = fromTeamId;
        this.toTeamId = toTeamId;
    }

    public static TeamLikeFindCommand of(Long fromTeamId, Long toTeamId) {
        return new TeamLikeFindCommand(fromTeamId, toTeamId);
    }
}
