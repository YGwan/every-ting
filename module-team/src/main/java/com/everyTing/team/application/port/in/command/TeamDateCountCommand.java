package com.everyTing.team.application.port.in.command;

import lombok.Getter;

@Getter
public class TeamDateCountCommand {

    private final Long teamId;

    private TeamDateCountCommand(Long teamId) {
        this.teamId = teamId;
    }

    public static TeamDateCountCommand of(Long teamId) {
        return new TeamDateCountCommand(teamId);
    }
}
