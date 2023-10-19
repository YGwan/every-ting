package com.everyTing.team.adapter.out.persistence.repository;

import com.everyTing.team.adapter.out.persistence.entity.TeamRequestEntity;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRequestEntityRepository extends JpaRepository<TeamRequestEntity, Long> {

    Boolean existsByFromTeamIdAndToTeamId(Long fromTeamId, Long toTeamId);

    Long countAllByCreatedAtAfter(LocalDateTime sinceDateTime);
}
