package com.everyTing.team.application.service;

import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_015;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.team.application.port.in.TeamDateUseCase;
import com.everyTing.team.application.port.in.command.TeamDateSaveCommand;
import com.everyTing.team.application.port.out.TeamDateCachePort;
import com.everyTing.team.application.port.out.TeamDatePort;
import com.everyTing.team.application.port.out.TeamMemberPort;
import com.everyTing.team.application.port.out.TeamRequestPort;
import com.everyTing.team.domain.TeamRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TeamDateService implements TeamDateUseCase {

    private final TeamDatePort teamDatePort;
    private final TeamDateCachePort teamDateCachePort;
    private final TeamMemberPort teamMemberPort;
    private final TeamRequestPort teamRequestPort;

    public TeamDateService(TeamDatePort teamDatePort, TeamDateCachePort teamDateCachePort,
        TeamMemberPort teamMemberPort, TeamRequestPort teamRequestPort) {
        this.teamDatePort = teamDatePort;
        this.teamDateCachePort = teamDateCachePort;
        this.teamMemberPort = teamMemberPort;
        this.teamRequestPort = teamRequestPort;
    }

    @Override
    @Transactional
    public Long saveTeamDate(TeamDateSaveCommand command) {
        final TeamRequest request = teamRequestPort.findTeamRequest(command.getRequestId());
        validateMemberIsToTeamLeader(command.getMemberId(), request.getToTeamId());

        final Long fromTeamId = request.getFromTeamId();
        final Long myTeamId = request.getToTeamId();
        final Long createdId = teamDatePort.saveTeamDate(fromTeamId, myTeamId);

        // 서로 한 요청 삭제
        teamRequestPort.removeTeamRequest(fromTeamId, myTeamId);
        teamRequestPort.removeTeamRequest(myTeamId, fromTeamId);

        teamDateCachePort.increaseDateCountsWithRedisLocks(fromTeamId, myTeamId);

        return createdId;
    }

    private void validateMemberIsToTeamLeader(Long memberId, Long toTeamId) {
        if (!teamMemberPort.existsTeamLeaderByTeamIdAndMemberId(toTeamId, memberId)) {
            throw new TingApplicationException(TEAM_015);
        }
    }
}
