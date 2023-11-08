package com.everyTing.team.application.port.in.command;

import lombok.Getter;

@Getter
public class TeamRequestCountCommand {

    private final Long teamId;

    private TeamRequestCountCommand(Long teamId) {
        this.teamId = teamId;
    }

    public static TeamRequestCountCommand of(Long teamId) {
        return new TeamRequestCountCommand(teamId);
    }
}
