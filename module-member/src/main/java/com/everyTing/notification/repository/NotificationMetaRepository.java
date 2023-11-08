package com.everyTing.notification.repository;

import com.everyTing.notification.domain.NotificationMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationMetaRepository extends JpaRepository<NotificationMeta, Long> {

    Optional<NotificationMeta> findByMemberId(Long memberId);
}
