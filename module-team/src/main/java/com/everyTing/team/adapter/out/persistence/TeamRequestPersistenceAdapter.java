package com.everyTing.team.adapter.out.persistence;

import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_019;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.team.adapter.out.persistence.entity.TeamEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamRequestEntity;
import com.everyTing.team.adapter.out.persistence.repository.TeamEntityRepository;
import com.everyTing.team.adapter.out.persistence.repository.TeamRequestEntityQueryRepository;
import com.everyTing.team.adapter.out.persistence.repository.TeamRequestEntityRepository;
import com.everyTing.team.application.port.out.TeamRequestPort;
import com.everyTing.team.domain.TeamRequest;
import com.everyTing.team.domain.TeamRequests;
import java.time.LocalDateTime;
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
    public Long saveTeamRequest(Long fromTeamId, Long toTeamId) {
        final TeamRequestEntity teamRequest = teamRequestEntityRepository.save(
            TeamRequestEntity.of(fromTeamId, toTeamId));
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

    @Override
    public Boolean existsTeamRequest(Long fromTeamId, Long toTeamId) {
        return teamRequestEntityRepository.existsByFromTeamIdAndToTeamId(fromTeamId, toTeamId);
    }

    @Override
    public Long countByFromTeamIdAndCreatedAtAfter(Long fromTeamId, LocalDateTime time) {
        final Long requestCount =
            teamRequestEntityRepository.countByFromTeamIdAndCreatedAtAfter(fromTeamId, time);
        return requestCount;
    }
}
