package com.everyTing.team.adapter.out.persistence;

import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_006;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_008;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_009;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_013;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.core.feign.dto.Member;
import com.everyTing.team.adapter.out.persistence.entity.TeamEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamMemberEntity;
import com.everyTing.team.adapter.out.persistence.entity.data.Role;
import com.everyTing.team.adapter.out.persistence.repository.TeamEntityRepository;
import com.everyTing.team.adapter.out.persistence.repository.TeamMemberEntityRepository;
import com.everyTing.team.application.port.out.TeamMemberPort;
import com.everyTing.team.domain.TeamMembers;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

@Repository
public class TeamMemberPersistenceAdapter implements TeamMemberPort {

    private final TeamMemberEntityRepository teamMemberEntityRepository;
    private final TeamEntityRepository teamEntityRepository;

    public TeamMemberPersistenceAdapter(TeamMemberEntityRepository teamMemberEntityRepository,
        TeamEntityRepository teamEntityRepository) {
        this.teamMemberEntityRepository = teamMemberEntityRepository;
        this.teamEntityRepository = teamEntityRepository;
    }

    @Override
    public Boolean existsTeamMemberByTeamLeaderId(Long memberId) {
        return teamMemberEntityRepository.existsByMemberIdAndRole(memberId, Role.LEADER);
    }

    @Override
    public TeamMembers findTeamMembers(Long teamId) {
        return TeamMembers.from(
            teamMemberEntityRepository.findAllByTeamIdOrderByRoleAscCreatedAtAsc(teamId));
    }

    @Override
    public Long saveTeamLeader(Long teamId, Long memberId) {
        final TeamMemberEntity created = teamMemberEntityRepository.save(
            TeamMemberEntity.of(teamId, memberId, Role.LEADER));

        return created.getId();
    }

    @Override
    public Long saveTeamMember(Long teamId, Member member) {
        validateTeamMemberIsNotDuplicate(teamId, member);

        TeamEntity teamEntity = fetchTeamWithPessimisticLock(teamId);
        validateTeamIsNotFull(teamEntity);
        validateMemberGender(teamEntity, member);

        final TeamMemberEntity createdTeamMember = teamMemberEntityRepository.save(
                TeamMemberEntity.of(teamId, member.getMemberId(), Role.MEMBER));
        teamEntity.increaseMemberNumber();

        return createdTeamMember.getId();
    }

    private TeamEntity fetchTeamWithPessimisticLock(Long teamId) {
        return teamEntityRepository.findByIdWithPessimisticLock(teamId)
                                   .orElseThrow(() -> new TingApplicationException(TEAM_006));
    }

    private void validateTeamMemberIsNotDuplicate(Long teamId, Member member) {
        if (teamMemberEntityRepository.existsByTeamIdAndMemberId(teamId, member.getMemberId())) {
            throw new TingApplicationException(TEAM_013);
        }
    }

    private void validateTeamIsNotFull(TeamEntity teamEntity) {
        if (teamEntity.isFull()) {
            throw new TingApplicationException(TEAM_008);
        }
    }

    private void validateMemberGender(TeamEntity teamEntity, Member member) {
        if (!teamEntity.getGender()
                       .equals(member.getGender())) {
            throw new TingApplicationException(TEAM_009);
        }
    }
}
