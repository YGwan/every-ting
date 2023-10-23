package com.everyTing.team.application.port.in.command;

import lombok.Getter;

@Getter
public class TeamMemberRemoveCommand {

    private final Long teamId;
    private final Long teamMemberId;
    private final Long memberId;

    private TeamMemberRemoveCommand(Long teamId, Long teamMemberId, Long memberId) {
        this.teamId = teamId;
        this.teamMemberId = teamMemberId;
        this.memberId = memberId;
    }

    public static TeamMemberRemoveCommand of(Long teamId, Long teamMemberId, Long memberId) {
        return new TeamMemberRemoveCommand(teamId, teamMemberId, memberId);
    }
}
