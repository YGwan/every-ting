package com.everyTing.team.adapter.out.persistence.repository;

import com.everyTing.team.adapter.out.persistence.entity.TeamHashtagEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamHashtagEntityRepository extends JpaRepository<TeamHashtagEntity, Long> {

    List<TeamHashtagEntity> findByTeamIdOrderByCreatedAt(Long teamId);
}
