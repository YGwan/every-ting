package com.everyTing.team.application.service;

import static com.everyTing.team.common.constraints.TeamConstraints.WEEKLY_DATE_LIMIT;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_011;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_015;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_020;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_021;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_022;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_023;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_024;
import static com.everyTing.team.common.exception.errorCode.TeamServerErrorCode.TSER_006;
import static com.everyTing.team.common.exception.errorCode.TeamServerErrorCode.TSER_007;

import com.everyTing.core.domain.Gender;
import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.core.exception.TingServerException;
import com.everyTing.team.application.port.in.TeamDateUseCase;
import com.everyTing.team.application.port.in.command.TeamDateCountCommand;
import com.everyTing.team.application.port.in.command.TeamDateSaveCommand;
import com.everyTing.team.application.port.out.TeamDatePort;
import com.everyTing.team.application.port.out.TeamMemberPort;
import com.everyTing.team.application.port.out.TeamPort;
import com.everyTing.team.application.port.out.TeamRequestPort;
import com.everyTing.team.domain.Team;
import com.everyTing.team.domain.TeamRequest;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.concurrent.TimeUnit;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TeamDateService implements TeamDateUseCase {

    private final TeamPort teamPort;
    private final TeamDatePort teamDatePort;
    private final TeamMemberPort teamMemberPort;
    private final TeamRequestPort teamRequestPort;
    private final RedissonClient redissonClient;

    @Value("${team.date.lock.key.prefix}")
    private String lockKeyPrefix;

    public TeamDateService(TeamPort teamPort, TeamDatePort teamDatePort,
        TeamMemberPort teamMemberPort,
        TeamRequestPort teamRequestPort, RedissonClient redissonClient) {
        this.teamPort = teamPort;
        this.teamDatePort = teamDatePort;
        this.teamMemberPort = teamMemberPort;
        this.teamRequestPort = teamRequestPort;
        this.redissonClient = redissonClient;
    }

    @Override
    public Long countRemainingTeamDate(TeamDateCountCommand command) {
        final Long count = teamDatePort.countByTeamIdAndCreatedAtAfter(command.getTeamId(),
            getPreviousSundayWithMaxTime());
        return WEEKLY_DATE_LIMIT - count;
    }

    @Override
    @Transactional
    public Long saveTeamDate(TeamDateSaveCommand command) {
        final TeamRequest request = teamRequestPort.findTeamRequest(command.getRequestId());
        validateMemberIsToTeamLeader(command.getMemberId(), request.getToTeamId());

        final Long fromTeamId = request.getFromTeamId();
        final Long myTeamId = request.getToTeamId();

        RLock locks = getLocks(fromTeamId, myTeamId);

        try {
            if (!locks.tryLock(5, 10, TimeUnit.SECONDS)) {
                throw new TingServerException(TSER_007);
            }

            return saveTeamDate(fromTeamId, myTeamId);
        } catch (InterruptedException e) {
            throw new TingServerException(TSER_006);
        } finally {
            locks.unlock();
        }
    }

    private void validateMemberIsToTeamLeader(Long memberId, Long toTeamId) {
        if (!teamMemberPort.existsTeamLeaderByTeamIdAndMemberId(toTeamId, memberId)) {
            throw new TingApplicationException(TEAM_015);
        }
    }

    private Long saveTeamDate(Long fromTeamId, Long myTeamId) {
        validateTeamDateCounts(fromTeamId, myTeamId);

        final Team fromTeam = teamPort.findTeamById(fromTeamId);
        final Team myTeam = teamPort.findTeamById(myTeamId);

        validateTeamsHaveDifferentGenders(fromTeam, myTeam);
        validateTeamsAreFull(fromTeam, myTeam);

        final Team womenTeam = fromTeam.getGender() == Gender.FEMALE ? fromTeam : myTeam;
        final Team menTeam = fromTeam.getGender() == Gender.MALE ? fromTeam : myTeam;

        validateTeamDateIsNotDuplicate(womenTeam, menTeam);

        final Long created = teamDatePort.saveTeamDate(womenTeam.getId(), menTeam.getId());
        teamRequestPort.removeTeamRequestsBetweenTeams(fromTeamId, myTeamId);

        return created;
    }

    private void validateTeamDateCounts(Long fromTeamId, Long myTeamId) {
        if (teamDatePort.countByTeamIdAndCreatedAtAfter(fromTeamId, getPreviousSundayWithMaxTime())
            >= WEEKLY_DATE_LIMIT) {
            throw new TingApplicationException(TEAM_024);
        }
        if (teamDatePort.countByTeamIdAndCreatedAtAfter(myTeamId, getPreviousSundayWithMaxTime())
            >= WEEKLY_DATE_LIMIT) {
            throw new TingServerException(TEAM_023);
        }
    }

    private void validateTeamsHaveDifferentGenders(Team fromTeam, Team myTeam) {
        if (fromTeam.getGender()
                    .equals(myTeam.getGender())) {
            throw new TingApplicationException(TEAM_011);
        }
    }

    private void validateTeamDateIsNotDuplicate(Team womenTeam, Team menTeam){
        if (teamDatePort.existsTeamDate(womenTeam.getId(), menTeam.getId())) {
            throw new TingApplicationException(TEAM_020);
        }
    }

    private void validateTeamsAreFull(Team fromTeam, Team myTeam) {
        if (!fromTeam.isFull()) {
            throw new TingApplicationException(TEAM_022);
        }
        if (!myTeam.isFull()) {
            throw new TingApplicationException(TEAM_021);
        }
    }

    private RLock getLocks(Long fromTeamId, Long myTeamId) {
        RLock fromTeamLock = redissonClient.getLock(getKey(fromTeamId));
        RLock myTeamLock = redissonClient.getLock(getKey(myTeamId));
        return redissonClient.getMultiLock(fromTeamLock, myTeamLock);
    }

    private String getKey(Long teamId) {
        return lockKeyPrefix + teamId;
    }

    private LocalDateTime getPreviousSundayWithMaxTime() {
        return (LocalDate.now()
                         .with(TemporalAdjusters.previous(DayOfWeek.SUNDAY))).atTime(LocalTime.MAX);
    }
}
