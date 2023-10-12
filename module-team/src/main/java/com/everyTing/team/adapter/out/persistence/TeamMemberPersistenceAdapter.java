package com.everyTing.team.adapter.out.persistence;

import com.everyTing.team.adapter.out.persistence.entity.TeamMemberEntity;
import com.everyTing.team.adapter.out.persistence.entity.data.Role;
import com.everyTing.team.adapter.out.persistence.repository.TeamMemberEntityRepository;
import com.everyTing.team.application.port.out.TeamMemberPort;
import com.everyTing.team.domain.TeamMembers;
import org.springframework.stereotype.Repository;

@Repository
public class TeamMemberPersistenceAdapter implements TeamMemberPort {

    private final TeamMemberEntityRepository teamMemberEntityRepository;

    public TeamMemberPersistenceAdapter(TeamMemberEntityRepository teamMemberEntityRepository) {
        this.teamMemberEntityRepository = teamMemberEntityRepository;
    }

    @Override
    public Boolean existsTeamMemberByTeamLeaderId(Long memberId) {
        return teamMemberEntityRepository.existsByMemberIdAndRole(memberId, Role.LEADER);
    }

    @Override
    public TeamMembers findTeamMembers(Long teamId) {
        return TeamMembers.from(teamMemberEntityRepository.findAllByTeamIdOrderByRoleAscCreatedAtAsc(teamId));
    }

    @Override
    public Long saveTeamLeader(Long teamId, Long memberId) {
        final TeamMemberEntity created = teamMemberEntityRepository.save(
            TeamMemberEntity.of(teamId, memberId, Role.LEADER));

        return created.getId();
    }
}
