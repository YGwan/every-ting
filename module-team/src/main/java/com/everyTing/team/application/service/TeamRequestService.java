package com.everyTing.team.application.service;

import static com.everyTing.team.common.constraints.TeamConstraints.REQUEST_MAX_LIMIT;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_014;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_015;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.team.application.port.in.TeamRequestUseCase;
import com.everyTing.team.application.port.in.command.TeamRequestFindCommand;
import com.everyTing.team.application.port.in.command.TeamRequestSaveCommand;
import com.everyTing.team.application.port.out.TeamMemberPort;
import com.everyTing.team.application.port.out.TeamRequestPort;
import com.everyTing.team.domain.TeamRequests;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TeamRequestService implements TeamRequestUseCase {

    private final TeamMemberPort teamMemberPort;
    private final TeamRequestPort teamRequestPort;

    public TeamRequestService(TeamMemberPort teamMemberPort, TeamRequestPort teamRequestPort) {
        this.teamMemberPort = teamMemberPort;
        this.teamRequestPort = teamRequestPort;
    }

    @Override
    @Transactional
    public Long saveTeamRequest(TeamRequestSaveCommand command) {
        validateMemberIsTeamLeader(command.getFromTeamId(), command.getMemberId());
        validateTodayRequestLimit(command.getFromTeamId());
        return teamRequestPort.saveTeamRequest(command.getFromTeamId(), command.getToTeamId());
    }

    private void validateMemberIsTeamLeader(Long teamId, Long memberId) {
        if (!teamMemberPort.existsTeamLeaderByTeamIdAndMemberId(teamId, memberId)) {
            throw new TingApplicationException(TEAM_015);
        }
    }

    private void validateTodayRequestLimit(Long teamId) {
        if (teamRequestPort.countTodayRequest(teamId) >= REQUEST_MAX_LIMIT) {
            throw new TingApplicationException(TEAM_014);
        }
    }

    @Override
    public TeamRequests findTeamRequest(TeamRequestFindCommand command) {
        return teamRequestPort.findTeamRequest(command.getFromTeamId(),
            command.getToTeamId());
    }
}
