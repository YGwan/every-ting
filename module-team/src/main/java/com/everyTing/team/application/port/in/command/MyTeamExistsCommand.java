package com.everyTing.team.application.port.in.command;

import lombok.Getter;

@Getter
public class MyTeamExistsCommand {

    private final Long memberId;

    private MyTeamExistsCommand(Long memberId) {
        this.memberId = memberId;
    }

    public static MyTeamExistsCommand of(Long memberId) {
        return new MyTeamExistsCommand(memberId);
    }
}
