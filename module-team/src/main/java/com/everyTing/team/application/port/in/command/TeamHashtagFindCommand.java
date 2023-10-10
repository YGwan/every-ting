package com.everyTing.team.application.port.in.command;

import lombok.Getter;

@Getter
public class TeamHashtagFindCommand {

    private final Long teamId;

    private TeamHashtagFindCommand(Long teamId) {
        this.teamId = teamId;
    }

    public static TeamHashtagFindCommand of(Long teamId) {
        return new TeamHashtagFindCommand(teamId);
    }
}
