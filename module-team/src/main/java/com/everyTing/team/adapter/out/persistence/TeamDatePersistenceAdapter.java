package com.everyTing.team.adapter.out.persistence;

import com.everyTing.team.adapter.out.persistence.entity.TeamDateEntity;
import com.everyTing.team.adapter.out.persistence.repository.TeamDateEntityRepository;
import com.everyTing.team.application.port.out.TeamDatePort;
import com.everyTing.team.domain.TeamDates;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TeamDatePersistenceAdapter implements TeamDatePort {

    private final TeamDateEntityRepository teamDateEntityRepository;

    public TeamDatePersistenceAdapter(TeamDateEntityRepository teamDateEntityRepository) {
        this.teamDateEntityRepository = teamDateEntityRepository;
    }

    @Override
    public Long countByTeamIdAndCreatedAtAfter(Long teamId, LocalDateTime time) {
        final Long dateCount = teamDateEntityRepository.countByWomenTeamIdOrMenTeamId(teamId, teamId);
        return dateCount;
    }

    @Override
    public Long saveTeamDate(Long womenTeamId, Long menTeamId) {
        final TeamDateEntity created = teamDateEntityRepository.save(
            TeamDateEntity.of(womenTeamId, menTeamId));

        return created.getId();
    }

    @Override
    public Boolean existsTeamDate(Long womenTeamId, Long menTeamId) {
        return teamDateEntityRepository.existsByWomenTeamIdAndMenTeamId(womenTeamId, menTeamId);
    }

    @Override
    public TeamDates findTeamDatesByTeamIdIn(List<Long> teamIds) {
        final List<TeamDateEntity> teamDateEntities =
            teamDateEntityRepository.findAllByWomenTeamIdInOrMenTeamIdInOrderByCreatedAtDesc(teamIds, teamIds);
        return TeamDates.from(teamDateEntities, teamIds);
    }
}
