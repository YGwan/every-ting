package com.everyTing.team.adapter.out.persistence.repository;

import com.everyTing.team.adapter.out.persistence.entity.TeamDateEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamDateEntityRepository extends JpaRepository<TeamDateEntity, Long> {

    Long countByWomenTeamIdOrMenTeamIdAndCreatedAtAfter(Long teamId1, Long teamId2, LocalDateTime time);

    List<TeamDateEntity> findAllByWomenTeamIdInOrMenTeamIdInOrderByCreatedAtDesc(
        List<Long> team1Ids, List<Long> team2Ids);

    Boolean existsByWomenTeamIdAndMenTeamId(Long womenTeamId, Long menTeamId);
}
