package com.everyTing.team.adapter.out.persistence.repository;

import com.everyTing.team.adapter.out.persistence.entity.TeamEntity;
import com.everyTing.team.adapter.out.persistence.entity.data.Code;
import java.util.Optional;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeamEntityRepository extends JpaRepository<TeamEntity, Long> {

    Optional<TeamEntity> findById(Long teamId);

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("select t from TeamEntity t where t.id = :teamId")
    Optional<TeamEntity> findByIdWithPessimisticLock(@Param("teamId") Long teamId);

    Optional<TeamEntity> findByCode(Code code);

    Boolean existsByCode(Code code);
}
