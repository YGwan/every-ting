package com.everyTing.team.adapter.out.persistence.repository;

import com.everyTing.team.adapter.out.persistence.entity.TeamLikeEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamMemberEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamLikeEntityRepository extends JpaRepository<TeamLikeEntity, Long> {

    List<TeamLikeEntity> findAllByFromTeamIdAndToTeamId(Long fromTeamId, Long toTeamId);

    Boolean existsByToTeamIdAndFromTeamMemberId(Long toTeamId, Long fromMemberId);

    void deleteByToTeamIdAndFromTeamMember(Long teamId, TeamMemberEntity teamMemberEntity);
}
