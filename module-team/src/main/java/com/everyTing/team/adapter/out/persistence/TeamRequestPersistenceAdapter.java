package com.everyTing.team.adapter.out.persistence;

import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_006;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_011;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_016;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_017;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_018;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_019;
import static java.time.LocalDateTime.now;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.team.adapter.out.persistence.entity.TeamEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamRequestEntity;
import com.everyTing.team.adapter.out.persistence.repository.TeamEntityRepository;
import com.everyTing.team.adapter.out.persistence.repository.TeamRequestEntityQueryRepository;
import com.everyTing.team.adapter.out.persistence.repository.TeamRequestEntityRepository;
import com.everyTing.team.application.port.out.TeamRequestPort;
import com.everyTing.team.domain.TeamRequest;
import com.everyTing.team.domain.TeamRequests;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TeamRequestPersistenceAdapter implements TeamRequestPort {

    private final TeamRequestEntityRepository teamRequestEntityRepository;
    private final TeamRequestEntityQueryRepository teamRequestEntityQueryRepository;
    private final TeamEntityRepository teamEntityRepository;

    public TeamRequestPersistenceAdapter(TeamRequestEntityRepository teamRequestEntityRepository,
        TeamRequestEntityQueryRepository teamRequestEntityQueryRepository,
        TeamEntityRepository teamEntityRepository) {
        this.teamRequestEntityRepository = teamRequestEntityRepository;
        this.teamRequestEntityQueryRepository = teamRequestEntityQueryRepository;
        this.teamEntityRepository = teamEntityRepository;
    }

    @Override
    public TeamRequests findTeamRequest(Long fromTeamId, Long toTeamId) {
        final List<TeamRequestEntity> teamRequestEntities =
            teamRequestEntityQueryRepository.findAllByFromTeamIdAndToTeamId(fromTeamId, toTeamId);
        return TeamRequests.from(teamRequestEntities);
    }

    @Override
    public TeamRequest findTeamRequest(Long requestId) {
        final TeamRequestEntity teamRequestEntity =
            teamRequestEntityRepository.findById(requestId)
                                       .orElseThrow(() -> new TingApplicationException(TEAM_019));
        return TeamRequest.from(teamRequestEntity);
    }

    @Override
    public Long countTodayRequest(Long fromTeamId) {
        final Long count = teamRequestEntityRepository.countAllByCreatedAtAfter(now().minusDays(1));
        return count;
    }

    @Override
    public Long saveTeamRequest(Long fromTeamId, Long toTeamId) {
        validateTeamRequestIsNotDuplicate(fromTeamId, toTeamId);

        final TeamEntity fromTeam = findTeamById(fromTeamId);
        final TeamEntity toTeam = findTeamById(toTeamId);

        validateTeamsHaveDifferentGenders(fromTeam, toTeam);
        validateTeamsAreFull(fromTeam, toTeam);

        final TeamRequestEntity teamRequest = teamRequestEntityRepository.save(
            TeamRequestEntity.of(fromTeam, toTeam));
        return teamRequest.getId();
    }

    @Override
    public void removeTeamRequest(Long fromTeamId, Long toTeamId) {
        teamRequestEntityRepository.deleteByFromTeamIdAndToTeamId(fromTeamId, toTeamId);
    }

    @Override
    public void removeTeamRequestsBetweenTeams(Long teamId1, Long teamId2) {
        teamRequestEntityQueryRepository.deleteAllRequestsBetweenTeams(teamId1, teamId2);
    }

    private void validateTeamRequestIsNotDuplicate(Long fromTeamId, Long toTeamId) {
        if (teamRequestEntityRepository.existsByFromTeamIdAndToTeamId(fromTeamId, toTeamId)) {
            throw new TingApplicationException(TEAM_016);
        }
    }

    private void validateTeamsHaveDifferentGenders(TeamEntity fromTeam, TeamEntity toTeam) {
        if (fromTeam.getGender()
                    .equals(toTeam.getGender())) {
            throw new TingApplicationException(TEAM_011);
        }
    }

    private void validateTeamsAreFull(TeamEntity fromTeam, TeamEntity toTeam) {
        if (!fromTeam.isFull()) {
            throw new TingApplicationException(TEAM_017);
        }
        if (!toTeam.isFull()) {
            throw new TingApplicationException(TEAM_018);
        }
    }

    private TeamEntity findTeamById(Long teamId) {
        return teamEntityRepository.findById(teamId)
                                   .orElseThrow(() -> new TingApplicationException(TEAM_006));
    }
}
