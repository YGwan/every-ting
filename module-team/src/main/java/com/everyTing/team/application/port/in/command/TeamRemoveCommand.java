package com.everyTing.team.application.port.in.command;

import lombok.Getter;

@Getter
public class TeamRemoveCommand {

    private final Long memberId;
    private final Long teamId;

    private TeamRemoveCommand(Long memberId, Long teamId) {
        this.memberId = memberId;
        this.teamId = teamId;
    }

    public static TeamRemoveCommand of(Long memberId, Long teamId) {
        return new TeamRemoveCommand(memberId, teamId);
    }
}
