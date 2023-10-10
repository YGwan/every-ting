package com.everyTing.team.application.port.in.command;

import lombok.Getter;

@Getter
public class TeamMemberFindCommand {

    private final Long teamId;

    private TeamMemberFindCommand(Long teamId) {
        this.teamId = teamId;
    }

    public static TeamMemberFindCommand of(Long teamId) {
        return new TeamMemberFindCommand(teamId);
    }
}
