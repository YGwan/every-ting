package com.everyTing.team.adapter.out.persistence.repository;

import com.everyTing.team.adapter.out.persistence.entity.TeamMemberEntity;
import com.everyTing.team.adapter.out.persistence.entity.data.Role;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamMemberEntityRepository extends JpaRepository<TeamMemberEntity, Long> {

    Boolean existsByTeamIdAndMemberId(Long teamId, Long memberId);

    Boolean existsByMemberIdAndRole(Long memberId, Role role);

    Boolean existsByTeamIdAndMemberIdAndRole(Long teamId, Long memberId, Role role);

    Boolean existsByIdAndTeamId(Long teamMemberId, Long teamId);

    List<TeamMemberEntity> findAllByMemberId(Long memberId);

    List<TeamMemberEntity> findAllByMemberIdAndRoleOrderByCreatedAt(Long memberId, Role role);

    List<TeamMemberEntity> findAllByTeamIdOrderByRoleAscCreatedAtAsc(Long teamId);
    
    Long countByTeamId(Long teamId);

    Optional<TeamMemberEntity> findByTeamIdAndMemberId(Long teamId, Long memberId);

    Optional<TeamMemberEntity> findByTeamIdAndRole(Long teamId, Role role);
}
