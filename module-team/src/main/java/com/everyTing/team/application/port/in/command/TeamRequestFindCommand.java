package com.everyTing.team.application.port.in.command;

import lombok.Getter;

@Getter
public class TeamRequestFindCommand {

    private final Long requestId;

    private TeamRequestFindCommand(Long requestId) {
        this.requestId = requestId;
    }

    public static TeamRequestFindCommand of(Long requestId) {
        return new TeamRequestFindCommand(requestId);
    }
}
