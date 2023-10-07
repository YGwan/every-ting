package com.everyTing.team.application.port.in.command;

import lombok.Getter;

@Getter
public class TeamFindCommand {

    private final Long teamId;

    private TeamFindCommand(Long teamId) {
        this.teamId = teamId;
    }

    public static TeamFindCommand of(Long teamId) {
        return new TeamFindCommand(teamId);
    }
}
