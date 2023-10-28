package com.everyTing.team.application.port.in.command;

import lombok.Getter;

@Getter
public class MyTeamRequestFindCommand {

    private final Long memberId;

    private MyTeamRequestFindCommand(Long memberId) {
        this.memberId = memberId;
    }

    public static MyTeamRequestFindCommand of(Long memberId) {
        return new MyTeamRequestFindCommand(memberId);
    }
}
