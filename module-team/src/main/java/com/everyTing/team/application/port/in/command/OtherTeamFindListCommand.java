package com.everyTing.team.application.port.in.command;

import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
public class OtherTeamFindListCommand {

    private final Long myTeamId;
    private final Pageable pageable;

    private OtherTeamFindListCommand(Long myTeamId, Pageable pageable) {
        this.myTeamId = myTeamId;
        this.pageable = pageable;
    }

    public static OtherTeamFindListCommand of(Long myTeamId, Pageable pageable) {
        return new OtherTeamFindListCommand(myTeamId, pageable);
    }
}
