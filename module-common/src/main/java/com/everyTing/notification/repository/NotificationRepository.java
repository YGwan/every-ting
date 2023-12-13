package com.everyTing.notification.repository;

import com.everyTing.notification.domain.Notification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Slice<Notification> findAllByMemberIdOrderByCreatedAtDesc(Long memberId, Pageable pageable);

    void deleteAllByMemberId(Long memberId);
}
