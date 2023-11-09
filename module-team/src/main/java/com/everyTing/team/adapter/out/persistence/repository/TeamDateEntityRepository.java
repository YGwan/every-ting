package com.everyTing.team.adapter.out.persistence.repository;

import com.everyTing.team.adapter.out.persistence.entity.TeamDateEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamDateEntityRepository extends JpaRepository<TeamDateEntity, Long> {

    Long countByWomenTeamIdOrMenTeamId(Long teamId1, Long teamId2);

    List<TeamDateEntity> findAllByWomenTeamIdInOrMenTeamIdInOrderByCreatedAtDesc(
        List<Long> team1Ids, List<Long> team2Ids);

    Boolean existsByWomenTeamIdAndMenTeamId(Long womenTeamId, Long menTeamId);
}
