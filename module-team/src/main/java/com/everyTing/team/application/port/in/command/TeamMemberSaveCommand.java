package com.everyTing.team.application.port.in.command;

import lombok.Getter;

@Getter
public class TeamMemberSaveCommand {

    private final Long teamId;
    private final Long memberId;

    private TeamMemberSaveCommand(Long teamId, Long memberId) {
        this.teamId = teamId;
        this.memberId = memberId;
    }

    public static TeamMemberSaveCommand of(Long teamId, Long memberId) {
        return new TeamMemberSaveCommand(teamId, memberId);
    }
}
