package com.everyTing.team.application.port.in.command;

import lombok.Getter;

@Getter
public class TeamDateSaveCommand {

    private final Long requestId;
    private final Long memberId;

    private TeamDateSaveCommand(Long requestId, Long memberId) {
        this.requestId = requestId;
        this.memberId = memberId;
    }

    public static TeamDateSaveCommand of(Long requestId, Long memberId) {
        return new TeamDateSaveCommand(requestId, memberId);
    }
}
