package com.everyTing.team.adapter.out.persistence.repository;

import com.everyTing.team.adapter.out.persistence.entity.TeamEntity;
import com.everyTing.team.adapter.out.persistence.entity.data.Code;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamEntityRepository extends JpaRepository<TeamEntity, Long> {

    Optional<TeamEntity> findByCode(Code code);
}
