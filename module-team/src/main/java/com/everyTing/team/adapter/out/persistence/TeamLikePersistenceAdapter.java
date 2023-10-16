package com.everyTing.team.adapter.out.persistence;

import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_006;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_010;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_012;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.team.adapter.out.persistence.entity.TeamEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamLikeEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamMemberEntity;
import com.everyTing.team.adapter.out.persistence.repository.TeamEntityRepository;
import com.everyTing.team.adapter.out.persistence.repository.TeamLikeEntityRepository;
import com.everyTing.team.adapter.out.persistence.repository.TeamMemberEntityRepository;
import com.everyTing.team.application.port.out.TeamLikePort;
import com.everyTing.team.common.exception.errorCode.TeamErrorCode;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

@Repository
public class TeamLikePersistenceAdapter implements TeamLikePort {

    private final TeamLikeEntityRepository teamLikeEntityRepository;
    private final TeamEntityRepository teamEntityRepository;
    private final TeamMemberEntityRepository teamMemberEntityRepository;

    public TeamLikePersistenceAdapter(TeamLikeEntityRepository teamLikeEntityRepository,
        TeamEntityRepository teamEntityRepository,
        TeamMemberEntityRepository teamMemberEntityRepository) {
        this.teamLikeEntityRepository = teamLikeEntityRepository;
        this.teamEntityRepository = teamEntityRepository;
        this.teamMemberEntityRepository = teamMemberEntityRepository;
    }

    @Override
    public Long saveTeamLike(Long toTeamId, Long fromTeamId, Long fromMemberId) {
        validateTeamLikeIsNotDuplicate(toTeamId, fromMemberId);

        final TeamEntity toTeam = findTeamById(toTeamId);
        final TeamEntity fromTeam = findTeamById(fromTeamId);

        validateTeamsHaveDifferentGenders(fromTeam, toTeam);

        final TeamMemberEntity fromTeamMember = findTeamMemberByTeamIdAndMemberId(fromTeamId,
            fromMemberId);
        final TeamLikeEntity created = teamLikeEntityRepository.save(
            TeamLikeEntity.of(fromTeamMember, fromTeam, toTeam));

        return created.getId();
    }

    private TeamEntity findTeamById(Long teamId) {
        return teamEntityRepository.findById(teamId)
                                   .orElseThrow(() -> new TingApplicationException(TEAM_006));
    }

    private void validateTeamLikeIsNotDuplicate(Long toTeamId, Long fromMemberId) {
        if (teamLikeEntityRepository.existsByToTeamIdAndFromTeamMemberId(toTeamId, fromMemberId)) {
            throw new TingApplicationException(TEAM_012);
        }
    }

    private void validateTeamsHaveDifferentGenders(TeamEntity fromTeam, TeamEntity toTeam) {
        if (fromTeam.getGender()
                  .equals(toTeam.getGender())) {
            throw new TingApplicationException(TeamErrorCode.TEAM_011);
        }
    }

    @Override
    public void removeTeamLike(Long toTeamId, Long fromTeamId, Long fromMemberId) {
        final TeamMemberEntity fromTeamMember = findTeamMemberByTeamIdAndMemberId(fromTeamId,
            fromMemberId);

        teamLikeEntityRepository.deleteByToTeamIdAndFromTeamMember(toTeamId, fromTeamMember);
    }

    private TeamMemberEntity findTeamMemberByTeamIdAndMemberId(Long teamId, Long memberId) {
        return teamMemberEntityRepository.findByTeamIdAndMemberId(teamId, memberId)
                                         .orElseThrow(() -> new TingApplicationException(TEAM_010));
    }
}
