package com.everyTing.team.application.port.in.command;

import lombok.Getter;

@Getter
public class TeamRequestRemoveCommand {

    private final Long requestId;
    private final Long memberId;

    private TeamRequestRemoveCommand(Long requestId, Long memberId) {
        this.requestId = requestId;
        this.memberId = memberId;
    }

    public static TeamRequestRemoveCommand of(Long requestId, Long memberId) {
        return new TeamRequestRemoveCommand(requestId, memberId);
    }
}
