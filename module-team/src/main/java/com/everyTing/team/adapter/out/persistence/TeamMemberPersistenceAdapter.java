package com.everyTing.team.adapter.out.persistence;

import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_006;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_008;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_009;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_010;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_013;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.core.feign.dto.Member;
import com.everyTing.team.adapter.out.persistence.entity.TeamEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamMemberEntity;
import com.everyTing.team.adapter.out.persistence.entity.data.Role;
import com.everyTing.team.adapter.out.persistence.repository.TeamEntityRepository;
import com.everyTing.team.adapter.out.persistence.repository.TeamMemberEntityRepository;
import com.everyTing.team.application.port.out.TeamMemberPort;
import com.everyTing.team.domain.TeamMember;
import com.everyTing.team.domain.TeamMembers;
import java.util.List;
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
    public Boolean existsTeamMemberByMemberId(Long memberId) {
        return teamMemberEntityRepository.existsByMemberId(memberId);
    }

    @Override
    public Boolean existsTeamMemberByTeamIdAndTeamMemberId(Long teamId, Long teamMemberId) {
        return teamMemberEntityRepository.existsByIdAndTeamId(teamMemberId, teamId);
    }

    @Override
    public Boolean existsTeamLeaderByMemberId(Long memberId) {
        return teamMemberEntityRepository.existsByMemberIdAndRole(memberId, Role.LEADER);
    }

    @Override
    public Boolean existsTeamLeaderByTeamIdAndMemberId(Long teamId, Long memberId) {
        return teamMemberEntityRepository.existsByTeamIdAndMemberIdAndRole(teamId, memberId, Role.LEADER);
    }

    @Override
    public TeamMembers findTeamMembersByTeamId(Long teamId) {
        final List<TeamMemberEntity> teamMemberEntities =
            teamMemberEntityRepository.findAllByTeamIdOrderByRoleAscCreatedAtAsc(teamId);

        return TeamMembers.from(teamMemberEntities);
    }

    @Override
    public TeamMembers findTeamMembersByMemberId(Long memberId) {
        final List<TeamMemberEntity> teamMemberEntities =
            teamMemberEntityRepository.findAllByMemberId(memberId);

        return TeamMembers.from(teamMemberEntities);
    }

    @Override
    public TeamMembers findTeamMembersByMemberIdAndRole(Long memberId, Role role) {
        final List<TeamMemberEntity> teamMemberEntities =
            teamMemberEntityRepository.findAllByMemberIdAndRoleOrderByCreatedAt(memberId, role);

        return TeamMembers.from(teamMemberEntities);
    }

    @Override
    public TeamMember findTeamMemberByTeamIdAndMemberId(Long teamId, Long memberId) {
        final TeamMemberEntity teamMember =
            teamMemberEntityRepository.findByTeamIdAndMemberId(teamId, memberId)
                                      .orElseThrow(
                                          () -> new TingApplicationException(TEAM_010));
        return TeamMember.from(teamMember);
    }

    @Override
    public TeamMember findTeamLeader(Long teamId) {
        final TeamMemberEntity teamLeader = teamMemberEntityRepository.findByTeamIdAndRole(teamId, Role.LEADER)
            .orElseThrow(() -> new TingApplicationException(TEAM_006));
        return TeamMember.from(teamLeader);
    }

    @Override
    public void removeTeamMember(Long teamId, Long teamMemberId) {
        teamMemberEntityRepository.deleteById(teamMemberId);

        TeamEntity teamEntity = fetchTeamWithPessimisticLock(teamId);
        teamEntity.decreaseMemberNumber();
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
                TeamMemberEntity.of(teamId, member.getId(), Role.MEMBER));
        teamEntity.increaseMemberNumber();

        return createdTeamMember.getId();
    }

    private TeamEntity fetchTeamWithPessimisticLock(Long teamId) {
        return teamEntityRepository.findByIdWithPessimisticLock(teamId)
                                   .orElseThrow(() -> new TingApplicationException(TEAM_006));
    }

    private void validateTeamMemberIsNotDuplicate(Long teamId, Member member) {
        if (teamMemberEntityRepository.existsByTeamIdAndMemberId(teamId, member.getId())) {
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
