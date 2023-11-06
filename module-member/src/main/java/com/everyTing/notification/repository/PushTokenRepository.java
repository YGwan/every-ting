package com.everyTing.notification.repository;

import com.everyTing.notification.domain.PushToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PushTokenRepository extends JpaRepository<PushToken, Long> {

    Optional<PushToken> findPushTokenByMemberId(Long memberId);
}
