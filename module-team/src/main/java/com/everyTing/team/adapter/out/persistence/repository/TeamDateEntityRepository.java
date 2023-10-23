package com.everyTing.team.adapter.out.persistence.repository;

import com.everyTing.team.adapter.out.persistence.entity.TeamDateEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamDateEntityRepository extends JpaRepository<TeamDateEntity, Long> {

    Boolean existsByWomenTeamAndMenTeam(TeamEntity womenTeam, TeamEntity menTeam);
}
