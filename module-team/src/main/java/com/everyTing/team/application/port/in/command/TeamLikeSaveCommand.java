package com.everyTing.team.application.port.in.command;

import lombok.Getter;

@Getter
public class TeamLikeSaveCommand {

    private final Long toTeamId;
    private final Long fromTeamId;
    private final Long fromMemberId;

    private TeamLikeSaveCommand(Long toTeamId, Long fromTeamId, Long fromMemberId) {
        this.toTeamId = toTeamId;
        this.fromTeamId = fromTeamId;
        this.fromMemberId = fromMemberId;
    }

    public static TeamLikeSaveCommand of(Long toTeamId, Long fromTeamId, Long fromMemberId) {
        return new TeamLikeSaveCommand(toTeamId, fromTeamId, fromMemberId);
    }
}
