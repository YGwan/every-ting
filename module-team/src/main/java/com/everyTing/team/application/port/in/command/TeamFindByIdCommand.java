package com.everyTing.team.application.port.in.command;

import lombok.Getter;

@Getter
public class TeamFindByIdCommand {

    private final Long teamId;

    private TeamFindByIdCommand(Long teamId) {
        this.teamId = teamId;
    }

    public static TeamFindByIdCommand of(Long teamId) {
        return new TeamFindByIdCommand(teamId);
    }
}
