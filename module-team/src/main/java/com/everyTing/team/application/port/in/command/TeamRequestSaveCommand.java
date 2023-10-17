package com.everyTing.team.application.port.in.command;

import lombok.Getter;

@Getter
public class TeamRequestSaveCommand {

    private final Long fromTeamId;
    private final Long toTeamId;
    private final Long memberId;

    private TeamRequestSaveCommand(Long fromTeamId, Long toTeamId, Long memberId) {
        this.fromTeamId = fromTeamId;
        this.toTeamId = toTeamId;
        this.memberId = memberId;
    }

    public static TeamRequestSaveCommand of(Long fromTeamId, Long toTeamId, Long memberId) {
        return new TeamRequestSaveCommand(fromTeamId, toTeamId, memberId);
    }
}
