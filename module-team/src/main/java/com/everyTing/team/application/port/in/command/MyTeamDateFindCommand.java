package com.everyTing.team.application.port.in.command;

import lombok.Getter;

@Getter
public class MyTeamDateFindCommand {

    private final Long memberId;

    private MyTeamDateFindCommand(Long memberId) {
        this.memberId = memberId;
    }

    public static MyTeamDateFindCommand of(Long memberId) {
        return new MyTeamDateFindCommand(memberId);
    }
}
