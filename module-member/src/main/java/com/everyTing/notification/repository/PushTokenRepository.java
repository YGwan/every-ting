package com.everyTing.notification.repository;

import com.everyTing.notification.domain.PushToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PushTokenRepository extends JpaRepository<PushToken, Long> {
}
