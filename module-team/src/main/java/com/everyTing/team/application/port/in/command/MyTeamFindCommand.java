package com.everyTing.team.application.port.in.command;

import com.everyTing.team.adapter.out.persistence.entity.data.Role;
import lombok.Getter;

@Getter
public class MyTeamFindCommand {

    private final Long memberId;
    private final Role myRole;

    private MyTeamFindCommand(Long memberId, Role myRole) {
        this.memberId = memberId;
        this.myRole = myRole;
    }

    public static MyTeamFindCommand of(Long memberId, Role myRole) {
        return new MyTeamFindCommand(memberId, myRole);
    }
}
