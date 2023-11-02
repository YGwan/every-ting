package com.everyTing.team.application.service;

import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_015;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.team.application.port.in.TeamHashtagUseCase;
import com.everyTing.team.application.port.in.command.TeamHashtagFindCommand;
import com.everyTing.team.application.port.in.command.TeamHashtagModifyCommand;
import com.everyTing.team.application.port.out.TeamHashtagPort;
import com.everyTing.team.application.port.out.TeamMemberPort;
import com.everyTing.team.domain.TeamHashtags;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TeamHashtagService implements TeamHashtagUseCase {

    private final TeamHashtagPort teamHashtagPort;
    private final TeamMemberPort teamMemberPort;

    public TeamHashtagService(TeamHashtagPort teamHashtagPort, TeamMemberPort teamMemberPort) {
        this.teamHashtagPort = teamHashtagPort;
        this.teamMemberPort = teamMemberPort;
    }

    @Override
    public TeamHashtags findHashtags(TeamHashtagFindCommand command) {
        return teamHashtagPort.findHashtags(command.getTeamId());
    }

    @Override
    @Transactional
    public void modifyHashtags(TeamHashtagModifyCommand command) {
        validateMemberIsTeamLeader(command.getTeamId(), command.getMemberId());

        teamHashtagPort.removeHashtags(command.getRemovedHashtagIds());
        teamHashtagPort.saveHashtags(command.getTeamId(), command.getNewHashtags());
    }

    private void validateMemberIsTeamLeader(Long teamId, Long memberId) {
        if (!teamMemberPort.existsTeamLeaderByTeamIdAndMemberId(teamId, memberId)) {
            throw new TingApplicationException(TEAM_015);
        }
    }
}
