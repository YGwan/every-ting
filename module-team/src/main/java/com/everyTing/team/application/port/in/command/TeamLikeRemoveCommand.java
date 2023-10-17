package com.everyTing.team.application.port.in.command;

import lombok.Getter;

@Getter
public class TeamLikeRemoveCommand {

    private final Long toTeamId;
    private final Long fromTeamId;
    private final Long fromMemberId;

    private TeamLikeRemoveCommand(Long toTeamId, Long fromTeamId, Long fromMemberId) {
        this.toTeamId = toTeamId;
        this.fromTeamId = fromTeamId;
        this.fromMemberId = fromMemberId;
    }

    public static TeamLikeRemoveCommand of(Long toTeamId, Long fromTeamId, Long fromMemberId) {
        return new TeamLikeRemoveCommand(toTeamId, fromTeamId, fromMemberId);
    }
}
