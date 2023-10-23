package com.everyTing.team.adapter.out.persistence;

import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_006;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_011;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_021;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_022;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_020;

import com.everyTing.core.domain.Gender;
import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.team.adapter.out.persistence.entity.TeamDateEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamEntity;
import com.everyTing.team.adapter.out.persistence.repository.TeamDateEntityRepository;
import com.everyTing.team.adapter.out.persistence.repository.TeamEntityRepository;
import com.everyTing.team.application.port.out.TeamDatePort;
import org.springframework.stereotype.Repository;

@Repository
public class TeamDatePersistenceAdapter implements TeamDatePort {

    private final TeamDateEntityRepository teamDateEntityRepository;
    private final TeamEntityRepository teamEntityRepository;

    public TeamDatePersistenceAdapter(TeamDateEntityRepository teamDateEntityRepository,
        TeamEntityRepository teamEntityRepository) {
        this.teamDateEntityRepository = teamDateEntityRepository;
        this.teamEntityRepository = teamEntityRepository;
    }

    @Override
    public Long saveTeamDate(Long fromTeamId, Long myTeamId) {
        final TeamEntity fromTeam = findTeamById(fromTeamId);
        final TeamEntity myTeam = findTeamById(myTeamId);

        validateTeamsHaveDifferentGenders(fromTeam, myTeam);
        validateTeamsAreFull(fromTeam, myTeam);

        final TeamEntity womenTeam = fromTeam.getGender() == Gender.FEMALE ? fromTeam : myTeam;
        final TeamEntity menTeam = fromTeam.getGender() == Gender.MALE ? fromTeam : myTeam;

        validateTeamDateIsNotDuplicate(womenTeam, menTeam);

        final TeamDateEntity created = teamDateEntityRepository.save(
            TeamDateEntity.of(womenTeam, menTeam));

        return created.getId();
    }

    private TeamEntity findTeamById(Long teamId) {
        return teamEntityRepository.findById(teamId)
                                   .orElseThrow(() -> new TingApplicationException(TEAM_006));
    }

    private void validateTeamDateIsNotDuplicate(TeamEntity womenTeam, TeamEntity menTeam){
        if (teamDateEntityRepository.existsByWomenTeamAndMenTeam(womenTeam, menTeam)) {
            throw new TingApplicationException(TEAM_020);
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
            throw new TingApplicationException(TEAM_022);
        }
        if (!toTeam.isFull()) {
            throw new TingApplicationException(TEAM_021);
        }
    }
}
