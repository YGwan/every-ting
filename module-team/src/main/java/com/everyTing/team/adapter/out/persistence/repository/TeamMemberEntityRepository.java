package com.everyTing.team.adapter.out.persistence.repository;

import com.everyTing.team.adapter.out.persistence.entity.TeamMemberEntity;
import com.everyTing.team.adapter.out.persistence.entity.data.Role;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamMemberEntityRepository extends JpaRepository<TeamMemberEntity, Long> {

    Boolean existsByMemberIdAndRole(Long memberId, Role role);

    List<TeamMemberEntity> findAllByTeamIdOrderByRoleAscCreatedAtAsc(Long teamId);
    
    Long countByTeamId(Long teamId);
}
