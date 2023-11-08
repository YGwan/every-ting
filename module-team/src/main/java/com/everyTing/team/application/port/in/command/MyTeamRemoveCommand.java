package com.everyTing.team.application.port.in.command;

import lombok.Getter;

@Getter
public class MyTeamRemoveCommand {

    private final Long teamId;
    private final Long memberId;

    private MyTeamRemoveCommand(Long teamId, Long memberId) {
        this.teamId = teamId;
        this.memberId = memberId;
    }

    public static MyTeamRemoveCommand of(Long teamId, Long memberId) {
        return new MyTeamRemoveCommand(teamId, memberId);
    }
}
