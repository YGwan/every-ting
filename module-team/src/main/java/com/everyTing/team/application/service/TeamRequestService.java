package com.everyTing.team.application.service;

import static com.everyTing.team.common.constraints.TeamConstraints.DAILY_REQUEST_LIMIT;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_011;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_014;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_015;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_017;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_018;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_020;
import static com.everyTing.team.common.exception.errorCode.TeamServerErrorCode.TSER_009;
import static com.everyTing.team.common.exception.errorCode.TeamServerErrorCode.TSER_010;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.core.exception.TingServerException;
import com.everyTing.team.application.port.in.TeamRequestUseCase;
import com.everyTing.team.application.port.in.command.TeamRequestCountCommand;
import com.everyTing.team.application.port.in.command.TeamRequestFindCommand;
import com.everyTing.team.application.port.in.command.TeamRequestRemoveCommand;
import com.everyTing.team.application.port.in.command.TeamRequestSaveCommand;
import com.everyTing.team.application.port.in.command.TeamRequestsFindCommand;
import com.everyTing.team.application.port.out.TeamMemberPort;
import com.everyTing.team.application.port.out.TeamPort;
import com.everyTing.team.application.port.out.TeamRequestPort;
import com.everyTing.team.domain.Team;
import com.everyTing.team.domain.TeamRequest;
import com.everyTing.team.domain.TeamRequests;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TeamRequestService implements TeamRequestUseCase {

    private final TeamRequestPort teamRequestPort;
    private final TeamPort teamPort;
    private final TeamMemberPort teamMemberPort;
    private final RedissonClient redissonClient;

    @Value("${team.request.lock.key.prefix}")
    private String lockKeyPrefix;

    public TeamRequestService(TeamRequestPort teamRequestPort, TeamPort teamPort,
        TeamMemberPort teamMemberPort, RedissonClient redissonClient) {
        this.teamRequestPort = teamRequestPort;
        this.teamPort = teamPort;
        this.teamMemberPort = teamMemberPort;
        this.redissonClient = redissonClient;
    }

    @Override
    public Long countRemainingTeamRequest(TeamRequestCountCommand command) {
        final Long count = teamRequestPort.countByFromTeamIdAndCreatedAtAfter(command.getTeamId(),
            getStartOfToday());
        return DAILY_REQUEST_LIMIT - count;
    }

    @Override
    public TeamRequest findTeamRequest(TeamRequestFindCommand command) {
        return teamRequestPort.findTeamRequest(command.getRequestId());
    }

    @Override
    public TeamRequests findTeamRequests(TeamRequestsFindCommand command) {
        return teamRequestPort.findTeamRequests(command.getFromTeamId(),
            command.getToTeamId());
    }

    @Override
    @Transactional
    public void removeTeamRequest(TeamRequestRemoveCommand command) {
        TeamRequest request = teamRequestPort.findTeamRequest(command.getRequestId());
        validateMemberIsTeamLeader(request.getFromTeamId(), request.getToTeamId(),
            command.getMemberId());

        teamRequestPort.removeTeamRequest(command.getRequestId());
    }

    private void validateMemberIsTeamLeader(Long fromTeamId, Long toTeamId, Long memberId) {
        final boolean isTeamLeader =
            teamMemberPort.existsTeamLeaderByTeamIdAndMemberId(fromTeamId, memberId)
                || teamMemberPort.existsTeamLeaderByTeamIdAndMemberId(toTeamId, memberId);

        if (!isTeamLeader) {
            throw new TingApplicationException(TEAM_015);
        }
    }

    @Override
    @Transactional
    public Long saveTeamRequest(TeamRequestSaveCommand command) {
        validateMemberIsTeamLeader(command.getFromTeamId(), command.getMemberId());
        validateTeamRequestIsNotDuplicate(command.getFromTeamId(), command.getToTeamId());

        final Long myTeamId = command.getFromTeamId();
        final Long toTeamId = command.getToTeamId();
        RLock lock = getLock(myTeamId);

        try {
            if (!lock.tryLock(3, 10, TimeUnit.SECONDS)) {
                throw new TingServerException(TSER_009);
            }

            return saveTeamRequest(myTeamId, toTeamId);
        } catch (InterruptedException e) {
            throw new TingServerException(TSER_010);
        } finally {
            lock.unlock();
        }
    }

    private void validateMemberIsTeamLeader(Long teamId, Long memberId) {
        if (!teamMemberPort.existsTeamLeaderByTeamIdAndMemberId(teamId, memberId)) {
            throw new TingApplicationException(TEAM_015);
        }
    }

    private void validateTeamRequestIsNotDuplicate(Long myTeamId, Long toTeamId) {
        if (teamRequestPort.existsTeamRequest(myTeamId, toTeamId)) {
            throw new TingApplicationException(TEAM_020);
        }
    }

    private Long saveTeamRequest(Long myTeamId, Long toTeamId) {
        validateMyTeamRequestCount(myTeamId);

        final Team myTeam = teamPort.findTeamById(myTeamId);
        final Team toTeam = teamPort.findTeamById(toTeamId);

        validateTeamsHaveDifferentGenders(myTeam, toTeam);
        validateTeamsAreFull(myTeam, toTeam);

        final Long createdId = teamRequestPort.saveTeamRequest(myTeamId, toTeamId);
        return createdId;
    }

    private void validateMyTeamRequestCount(Long myTeamId) {
        if (teamRequestPort.countByFromTeamIdAndCreatedAtAfter(myTeamId, getStartOfToday())
            >= DAILY_REQUEST_LIMIT) {
            throw new TingApplicationException(TEAM_014);
        }
    }

    private void validateTeamsHaveDifferentGenders(Team myTeam, Team toTeam) {
        if (myTeam.getGender()
                    .equals(toTeam.getGender())) {
            throw new TingApplicationException(TEAM_011);
        }
    }

    private void validateTeamsAreFull(Team myTeam, Team toTeam) {
        if (!myTeam.isFull()) {
            throw new TingApplicationException(TEAM_017);
        }
        if (!toTeam.isFull()) {
            throw new TingApplicationException(TEAM_018);
        }
    }

    private RLock getLock(Long teamId) {
        RLock lock = redissonClient.getLock(getKey(teamId));
        return lock;
    }

    private String getKey(Long teamId) {
        return lockKeyPrefix + teamId;
    }

    private LocalDateTime getStartOfToday() {
        return LocalDate.now()
                        .atStartOfDay();
    }
}
